package quarter.perfect.com.quarternew.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.kiss.ApiService;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import quarter.perfect.com.quarternew.HomeActivity;
import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.bean.ShangChuanBean;
import quarter.perfect.com.quarternew.util.APIs;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class QuarterPublishActivity extends AppCompatActivity {


    private File videoFile;  // 视屏资源
    private File coverFile;  // 视屏封面
    private String videoDesc = "这个视屏非常好看！";  // 视屏描述
    private String uid = "13563";  // uid   废物的id
    private String token = "26BD55D2E872C840EA697151F31BE06C";  // 用户令牌

//    private String uid = "12400";  // uid    废物的id
//    private String token = "51690705FDAC4132A3BD7453D134682D";  // 用户令牌

//    private String uid = "13664";  // uid   我的id
//    private String token = "EDBC3B54F2E9E375E50A76E8E3F8E483";  // 用户令牌

    private String latitude="39.95";   // 纬度
    private String longitude="116.30";   // 经度
    private Button fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cancelTitle();
        fa = findViewById(R.id.fa);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        String[] split = path.split("/0");
        String s = split[1];
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shangChuanVideo();
            }
        });
        File f1 = Environment.getExternalStorageDirectory();

        videoFile = new File(f1, s);   // 视屏资源
        File f2 = Environment.getExternalStorageDirectory();
        coverFile = new File(f2, s);

    }
    public void shangChuanVideo(){
        if(!latitude.equals("")&&!longitude.equals("")){
            Log.i("jiba",latitude+"====="+longitude);
            Log.i("jiba","videoFile====="+videoFile);
            Log.i("jiba","coverFile====="+coverFile);

            // 这是为了打印retrofit的log日志
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.i("RetrofitLog","retrofitBack = "+message);
                }
            });
            // 对log 的初始化
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // log 属于拦截器 所以需要将他注入okhttp中
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .build();
            // 初始化retrofit
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIs.debug)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            // 通过反射获得 apiservice接口
            ApiService apiService = retrofit.create(ApiService.class);

            // 将参数整合 拼接
            HashMap<String, String> map = new HashMap<>();
            map.put("uid",uid);
            map.put("videoDesc",videoDesc);
            map.put("latitude",latitude);
            map.put("longitude",longitude);
            map.put("token",token); // ?source=android&appVersion=101
            map.put("source","android");
            map.put("appVersion","101");

            String oldUrl = APIs.SHANGCHUAN;
            StringBuilder stringBuilder = new StringBuilder();//创建一个stringBuilder
            stringBuilder.append(oldUrl);

            if (oldUrl.contains("?")){
                //?在最后面....2类型
                if (oldUrl.indexOf("?") == oldUrl.length()-1){

                }else {
                    //3类型...拼接上&
                    stringBuilder.append("&");
                }
            }else {
                //不包含? 属于1类型,,,先拼接上?号
                stringBuilder.append("?");
            }

            for (Map.Entry<String,String> entry: map.entrySet()) {
                //拼接
                stringBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            //删掉最后一个&符号
            if (stringBuilder.indexOf("&") != -1){
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
            }
            String newUrl = stringBuilder.toString();//新的路径

            // 凡是经过file上传的参数，我都需要对其进行指示，也可以理解为对他进行备注，转码

            // 这这个接口中，我们发现有两个参数是通过file类型上传的，所以我们可以理解为这是一个多文件上传

            // 首先是对文件视频的备注 ，

            // 备注分为三个步骤 1，转为请求体 2.指定真正的参数名 与 实际名   3. 为file类型添加描述
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), videoFile);

            // 2.指定真正的参数名 与 实际名
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("videoFile", videoFile.getName(), requestFile);

            // 3. 为file类型添加字段描述
            String descriptionString = "hello, 这是文件视屏";
            RequestBody description =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), descriptionString);

            // 为文件封面进行转码，备注

            RequestBody requestFile1 =
                    RequestBody.create(MediaType.parse("multipart/form-data"), coverFile);

            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body1 =
                    MultipartBody.Part.createFormData("coverFile", coverFile.getName(), requestFile1);

            // 添加描述
            String descriptionString1 = "hello, 这是文件封面";
            RequestBody description1 =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), descriptionString1);

            /**
             *  newUrl == 请求路径
             *
             *  description == 为视屏文件的字段描述
             *
             *  body == 为视频文件的请求体
             *
             *  description1== 为封面文件的字段描述
             *
             *  body1==  为封面文件的请求体
             *
             */
            Observable<String> responseBodyCall = apiService.uploadFile(newUrl,description,body,description1,body1);

            responseBodyCall.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
                            ShangChuanBean shangChuanBean = gson.fromJson(result, ShangChuanBean.class);
                            Toast.makeText(QuarterPublishActivity.this,shangChuanBean.getMsg(),Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(QuarterPublishActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    });
        }
    }
    private void cancelTitle() {
        //取消标题栏
        getSupportActionBar().hide();
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
