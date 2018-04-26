package com.example.xiaohe.base.kiss;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by xiaohe on 2018/4/13.
 */

public interface ApiService {
    /**
     * Get基本请求,这里从Call改为Observable被观察者
     * @param url
     * @return
     */
    @GET
    public Observable<String> get(@Url String url);

    /**
     * Get请求提交表单
     * @param url
     * @param map
     * @return
     */
    @GET
    public Observable<String> get(@Url String url, @QueryMap Map<String,String> map);

    /**
     * Post请求提交表单
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    public Observable<String> post(@Url String url , @FieldMap Map<String,String> map);

    /**
     * 上传视屏所需参数如下  这种方法适用于多文件上传
     *   uid  	    string	是	用户id       13563
     videoFile	File	是	视频文件
     coverFile	File	是	视频封面
     videoDesc	String	否	视频描述
     latitude	String	是	高德纬度坐标
     longitude	String	是	高德经度坐标
     token	    String	是	用户令牌      26BD55D2E872C840EA697151F31BE06C
     *
     *  写的是个demo所有我把所有值都写成死的了，除了经纬度
     *
     */

    /**
     *
     * @param url  请求路径
     * @param description1   为视屏文件的字段描述
     * @param file1  为视频文件的请求体
     * @param description2   为封面文件的字段描述
     * @param file2  为封面文件的请求体
     * @return
     */
    @Multipart
    @POST
    Observable<String> uploadFile(
            @Url String url,
            @Part("description") RequestBody description1,
            @Part MultipartBody.Part file1,
            @Part("description") RequestBody description2,
            @Part MultipartBody.Part file2);

    @Multipart
    @POST("quarter/publishJoke")
    Observable<String> uploadImag(
            @PartMap Map<String,RequestBody> map1);
}
