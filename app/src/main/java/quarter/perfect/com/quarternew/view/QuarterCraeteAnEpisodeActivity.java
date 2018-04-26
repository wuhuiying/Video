package quarter.perfect.com.quarternew.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.kiss.ApiService;
import com.google.gson.Gson;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import quarter.perfect.com.quarternew.HomeActivity;
import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.app.MyApp;
import quarter.perfect.com.quarternew.bean.QuarterDuanziBean;
import quarter.perfect.com.quarternew.util.APIs;
import quarter.perfect.com.quarternew.util.HttpParameterBuilder;
import quarter.perfect.com.quarternew.util.UtilsImageProcess;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class QuarterCraeteAnEpisodeActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.content)
    EditText content;
    private GridView gridView1;              //网格显示缩略图
    private String pathImage;                //选择图片路径
    private Bitmap bmp;                      //导入临时图片
    private View v;
    private PopupWindow popupWindow;
    private String path;
    private ArrayList<String> images=new ArrayList<>();
    private ArrayList<File> files=new ArrayList<>();
    private ArrayList<HashMap<String, Object>> imageItem;
    private SimpleAdapter simpleAdapter;//适配器
    private String wenzi;
    private Uri uri;
    private int aa=9;
    private SharedPreferences quarterLogin;
    private String uid;
    private String token;
    private HttpParameterBuilder params;
    private HttpParameterBuilder jokeFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_craete_an_episode);
        ButterKnife.bind(this);
        cancelTitle();
        quarterLogin = getSharedPreferences("quarterLogin", 0);
        uid = quarterLogin.getString("uid", "");
        token = quarterLogin.getString("token", "");
        /*
         * 防止键盘挡住输入框
         * 不希望遮挡设置activity属性 android:windowSoftInputMode="adjustPan"
         * 希望动态调整高度 android:windowSoftInputMode="adjustResize"
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_ADJUST_PAN);
        initData();

    }
    public void initData() {

        //获取控件对象
        gridView1 = findViewById(R.id.gridView1);
        //获取uid
        /*
         * 载入默认图片添加图片加号
         * 通过适配器实现
         * SimpleAdapter参数imageItem为数据源 R.layout.griditem_addpic为布局
         */
        //获取资源图片加号
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.jia);
        imageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        setSimAdaper();
        gridView1.setAdapter(simpleAdapter);

        /*
         * 监听GridView点击事件
         * 报错:该函数必须抽象方法 故需要手动导入import android.view.View;
         */
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {

                if(position == 0) {
                    if(imageItem.size() == 10) { //第一张为默认图片
                        Toast.makeText(QuarterCraeteAnEpisodeActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //点击图片位置为+ 0对应0张图片
                        setPop();
                    }
                }
                else {
                    dialog(position);
                }
            }
        });
    }

    //设置适配器
    private void setSimAdaper() {
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.griditem_addpic,
                new String[] { "itemImage"}, new int[] { R.id.imageView1});
        /*
         * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
         * map.put("itemImage", R.drawable.img);
         * 解决方法:
         *              1.自定义继承BaseAdapter实现
         *              2.ViewBinder()接口实现
         *  参考 http://blog.csdn.net/admin_/article/details/7257901
         */
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                // TODO Auto-generated method stub
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView i = (ImageView)view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
    }

    //找到pop里面的选项
    private void setPop() {
        //弹出popupWindow
        v = LayoutInflater.from(this).inflate(R.layout.pp, null);
        //找到pop里面的选项
        Button pai = v.findViewById(R.id.btn_take_photo);
        Button xuan = v.findViewById(R.id.btn_pick_photo);
        Button qu =v.findViewById(R.id.btn_cancel);
        pai.setOnClickListener(this);
        xuan.setOnClickListener(this);
        qu.setOnClickListener(this);
        popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(android.R.color.transparent)));
        popupWindow.setOutsideTouchable(true);
        //显示
        popupWindow.showAtLocation(QuarterCraeteAnEpisodeActivity.this.findViewById(R.id.main),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    @OnClick({R.id.back6,R.id.fabiao})
    public void btn(View v){
        switch (v.getId()){
            case R.id.back6:
                finish();
                break;
            case R.id.fabiao:
                wenzi = content.getText().toString().trim();
                if(TextUtils.isEmpty(wenzi)){
                    Toast.makeText(this,"文字不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                postFile();
                break;
        }
    }

    //上传图片
    public void postFile(){
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        // 对log 的初始化
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(new MyInter())
                .build();
        // 初始化retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIs.debug)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //准备请求的参数,接口路径   HttpParameterBuilder工具类 之前的上传头像中有此工具类

        // 通过反射获得 apiservice接口
        ApiService apiService = retrofit.create(ApiService.class);
        params = HttpParameterBuilder.newBuilder();
        params.addParameter("uid", uid);
        params.addParameter("token", token);
        params.addParameter("content", wenzi);
        params.addParameter("source","android");
        params.addParameter("appVersion","101");
        for (int i = 0; i < files.size(); i++) {
           params.addParameter("jokeFiles", files.get(i));
        }


        apiService.uploadImag(params.bulider())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        QuarterDuanziBean quarterDuanziBean = gson.fromJson(result, QuarterDuanziBean.class);
                        Toast.makeText(QuarterCraeteAnEpisodeActivity.this,quarterDuanziBean.getMsg(),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(QuarterCraeteAnEpisodeActivity.this, HomeActivity.class));
                    }

                               @Override
                               public void onError(@NonNull Throwable e) {
                                   super.onError(e);
                                   Log.e("uploadImag", "onSuccess: "+e.toString());
                               }
                           }
                );
        //apiService.uploadImag()
        //调取工具类更新接口数据
        // p.FaBu(params.bulider());
    }
   @Override
    public void onClick(View v) {
        switch (v.getId()){
            //调用相机
            case R.id.btn_take_photo:
                //首先创建一个路径
                path= UtilsImageProcess.getSavePath();
                //转换成uri路径
                uri = Uri.fromFile(new File(path));
                //打开相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 666);
                popupWindow.dismiss();
                break;
            //调用相册
            case R.id.btn_pick_photo:
                ImageSelectorUtils.openPhoto(QuarterCraeteAnEpisodeActivity.this,999, false, aa);
                popupWindow.dismiss();
                break;
            case R.id.btn_cancel:
                Toast.makeText(this,"取消了",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
        }
    }
    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 999:
                //获取选择器返回的数据
                images = data.getStringArrayListExtra(
                        ImageSelectorUtils.SELECT_RESULT);
                //设置上传的信息
                setImages(images);
                break;
            case 666:
                //拿到相片
                Bitmap bitmap = UtilsImageProcess.getSmallBitmap(path);
                //通过工具把相片转换成uri
                uri = UtilsImageProcess.saveBitmap(path,bitmap);
                //调裁剪的第三方
                beginCrop(uri);
                break;
            case Crop.REQUEST_CROP:
                //裁剪完成的方法
                handleCrop(data);
                break;
        }
    }
    //设置展示页面的信息
    private void setImages(ArrayList<String> images) {
        for (int i = 0; i <images.size() ; i++) {
            Uri uri = Uri.parse(images.get(i));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", uri);
            imageItem.add(map);
            files.add(new File(images.get(i)));
        }
        aa-=images.size();
        setSimAdaper();
        gridView1.setAdapter(simpleAdapter);
        //清空数据,不然会重复添加
        images.clear();
        //刷新后释放防止手机休眠后自动添加
        path = null;
    }
    //启动裁剪Activity
    private void beginCrop(Uri source) {
        Crop.of(source, uri).asSquare().start(this);
    }

    //处理裁剪得到的图片,上传到服务器
    private void handleCrop(Intent data) {
        Uri u = Crop.getOutput(data);
        try {
            //拿到剪裁之后的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), u);
            //通过工具把bitmap转换为绝对路径,创建文件上传服务器
            String imagePath = UtilsImageProcess.getPath(path,bitmap);
            //通过工具类把bitmap转换为uri方便直接给控件赋值file = new File(imagePath);
            images.add(imagePath);
            // 这里我们发送广播让MediaScanner 扫描我们制定的文件
            // 这样在系统的相册中我们就可以找到我们拍摄的照片了
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(uri);
            this.sendBroadcast(intent);
            //调设置展示的信息并保存到files数组
            setImages(images);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * Dialog对话框提示用户删除操作
    * position为删除图片位置
    */
    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuarterCraeteAnEpisodeActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                files.remove(position-1);
                aa+=1;
                simpleAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //拦截器
    static class MyInter implements Interceptor {

        private int versionCode;
        private Context context;
        private SharedPreferences jiLu;
        private String token;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder request_builder = request.newBuilder();

            if ("GET".equals(request.method())) {

                HttpUrl.Builder builder = request.url().newBuilder();
                HttpUrl build = builder
                        .build();
                request = request_builder.url(build).build();

            }


            if ("POST".equals(request.method())) {
                if (request.body() instanceof FormBody) {
                    System.out.println("FormBody开始添加公共参数");
                    FormBody.Builder builder = new FormBody.Builder();
                    FormBody body = (FormBody) request.body();

                    for (int i = 0; i < body.size(); i++) {
                        builder.add(body.encodedName(i), body.encodedValue(i));
                    }
                    request = request_builder.post(body).build();

                }
                //因为传送文件要用到 @Multipart注解
                else if (request.body() instanceof MultipartBody) {
                    MultipartBody body = (MultipartBody) request.body();
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    List<MultipartBody.Part> parts = body.parts();
                    for (MultipartBody.Part part : parts) {
                        builder.addPart(part);
                    }
                    request = request_builder.post(builder.build()).build();
                }
            }

            return chain.proceed(request);

        }
    }
    private void cancelTitle() {
        //取消标题栏
        getSupportActionBar().hide();
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
