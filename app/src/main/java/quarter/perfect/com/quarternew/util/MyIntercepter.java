package quarter.perfect.com.quarternew.util;

import android.content.pm.PackageInfo;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;



/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/28
 * decription:开发
 */

public class MyIntercepter implements Interceptor {
    private final String TAG = "respond";
private int versionCode;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder request_builder = request.newBuilder();
        System.out.println(request.method() + "开始添加公共参数222222222");

        if("GET".equals(request.method()))
        {

            HttpUrl.Builder builder = request.url().newBuilder();
            HttpUrl build = builder.addQueryParameter("source", "android")
                    .addQueryParameter("appVersion", versionCode + "")

                    .build();
           request = request_builder.url(build).build();

        }


        if ("POST".equals(request.method())) {

            System.out.println(request.method() + "开始添加公共参数3333333333"+request.body().toString());
            if (request.body() instanceof FormBody) {
                System.out.println("FormBody开始添加公共参数");
                FormBody.Builder builder = new FormBody.Builder();
                FormBody body = (FormBody) request.body();

                for (int i = 0; i < body.size(); i++) {
                    builder.add(body.encodedName(i), body.encodedValue(i));
                }

                body = builder.add("source", "android")
                        .add("appVersion", String.valueOf(versionCode))

                        .build();
                System.out.println("开始添加公共参数55555" );
                request = request_builder.post(body).build();

            }
            else if(request.body() instanceof MultipartBody)
            {
                System.out.println("MultipartBody开始添加公共参数");
                MultipartBody body = (MultipartBody) request.body();
                MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("source","android")
                        .addFormDataPart("appVersion",101+"");

                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                request=request_builder.post(builder.build()).build();
            }
        }

//            System.out.println("开始添加公共参数44444444444" + chain.proceed(request).body().string());

            return chain.proceed(request);

    }

    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addParam(Request oldRequest) {


        PackageInfo packageArchiveInfo = Myapp.context.getPackageManager().getPackageArchiveInfo(Myapp.context.getPackageName(), 0);
        int versionCode = packageArchiveInfo.versionCode;

        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("source", "android")
                .setEncodedQueryParameter("appVersion",101+"")
                ;

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();

        return newRequest;
    }


}
