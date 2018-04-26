package quarter.perfect.com.quarternew.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by Administrator on 2018/3/24.
 */

public class Util {
    private volatile static Util util=null;
    private Util(){

    }
    public static Util getmInstance(){
        if (util==null){
            synchronized (Util.class){
                if (util==null){
                    util=new Util();
                }
            }
        }
        return util;
    }

    OkHttpClient httpClient = new OkHttpClient.Builder().build();
//            addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .addInterceptor(new MyIntercepter())
//            .build();

    public Testserivce getnetjson(String uri){ Retrofit retrofit = new Retrofit.Builder().baseUrl(uri)
//            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build();

        Testserivce testservive = retrofit.create(Testserivce.class);
        return testservive;}


   }
