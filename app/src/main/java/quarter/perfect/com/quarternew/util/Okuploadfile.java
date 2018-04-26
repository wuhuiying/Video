package quarter.perfect.com.quarternew.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/6.
 */

public class Okuploadfile {
    /**
     *
     *
     * post请求方式一：string类型参数请求
     */
    public void getUserInfo() {
        //post请求方式一：application/x-www-form-urlencoded（只能上传string类型的参数，不能上传文件）
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", "71");

        Request request = new Request.Builder().post(builder.build()).url(Getnet.net).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    System.out.println("getuserinfo:"+response.body().string());
                }
            }
        });




    }

    /**
     * post方式二：stirng类型参数和上传文件参数
     */
    public void upload(Map<String, Object> params) {
        //post请求方式二：multipart/form-data(不仅能够上传string类型的参数，还可以上传文件（流的形式，file）)
        OkHttpClient okHttpClient1 = new OkHttpClient();
        MultipartBody.Builder builder1 = new MultipartBody.Builder();
        builder1.setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            if (value instanceof File) {//如果请求的值是文件
                File file = (File) value;
                //MediaType.parse("application/octet-stream")以二进制的形式上传文件
                builder1.addFormDataPart("jokeFiles", ((File) value).getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
            } else {//如果请求的值是string类型
                builder1.addFormDataPart(key, value.toString());
            }
        }


        Request request1 = new Request.Builder().post(builder1.build()).url(Getnet.net).build();

        okHttpClient1.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                System.out.println("falare:==========");

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    System.out.println("fileuploadsuccess：" + response.body().string());
                }
            }
        });

    }

}
