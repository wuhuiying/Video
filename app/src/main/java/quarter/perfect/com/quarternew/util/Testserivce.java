package quarter.perfect.com.quarternew.util;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import quarter.perfect.com.quarternew.bean.Upload;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/3/24.
 */

public interface Testserivce {

    @Multipart
    @POST("quarter/publishVideo")
    Observable<Upload> faBuDaunzi(@PartMap Map<String, RequestBody> params);

    //第二种方式上传头像
   @POST("quarter/publishVideo")
    @Multipart
    Observable<Upload> upload2(@QueryMap Map<String, String> map, @Part MultipartBody.Part file, @Part MultipartBody.Part file1);



}
