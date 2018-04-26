package com.example.xiaohe.base.util;

import android.util.Log;

import com.example.xiaohe.base.kiss.ApiService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.FieldMap;

/**
 * Created by xiaohe on 2018/4/13.
 */

public class RetrofitAndOkhttpAndRxAndriodUtil {
    //使全局就一个OKHttpClient对象
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .cookieJar(new CookiesManager())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    //获取到请求
                    Request request = chain.request();
                    //获取请求的方式
                    String method = request.method();
                    //获取请求的路径
                    String oldUrl = request.url().toString();

                    Log.e("---拦截器",request.url()+"---"+request.method()+"--"+request.header("User-agent"));

                    //要添加的公共参数...map
                    Map<String,String> map = new HashMap<>();
                    map.put("source","android");

                    if ("GET".equals(method)){
                        // 1.http://www.baoidu.com/login                --------？ key=value&key=value
                        // 2.http://www.baoidu.com/login?               --------- key=value&key=value
                        // 3.http://www.baoidu.com/login?mobile=11111    -----&key=value&key=value

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

                        //添加公共参数....
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

                        //拿着新的路径重新构建请求
                        request = request.newBuilder()
                                .url(newUrl)
                                .build();



                    }else if ("POST".equals(method)){
                        //先获取到老的请求的实体内容
                        RequestBody oldRequestBody = request.body();//....之前的请求参数,,,需要放到新的请求实体内容中去

                        //如果请求调用的是上面doPost方法
                        if (oldRequestBody instanceof FormBody){
                            FormBody oldBody = (FormBody) oldRequestBody;

                            //构建一个新的请求实体内容
                            FormBody.Builder builder = new FormBody.Builder();
                            //1.添加老的参数
                            for (int i=0;i<oldBody.size();i++){
                                builder.add(oldBody.name(i),oldBody.value(i));
                            }
                            //2.添加公共参数
                            for (Map.Entry<String,String> entry:map.entrySet()) {

                                builder.add(entry.getKey(),entry.getValue());
                            }

                            FormBody newBody = builder.build();//新的请求实体内容

                            //构建一个新的请求
                            request = request.newBuilder()
                                    .url(oldUrl)
                                    .post(newBody)
                                    .build();
                        }


                    }
                    Response response = chain.proceed(request);

                    return response;
                }
            })
            .build();
    //使全局就一个Retrofit对象,设置基础Url
    public static ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://qbh.2dyt.com")
            //使我们能高度自定义转化器
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            //把 以前的 call 转化成 Observable,这是Retrofit与RxJava结合使用的关键
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService.class);
    public static Observable<String> get(String url) {
        return apiService.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<String> get(String url, Map<String, String> map) {
        return apiService.get(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<String> post(String url, Map<String, String> map) {
        return apiService.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<String> loadFile(String url, RequestBody description1, MultipartBody.Part file1,RequestBody description2,MultipartBody.Part file2, Map<String,String> map) {
        return apiService.uploadFile(url, description1,file1,description2,file2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
