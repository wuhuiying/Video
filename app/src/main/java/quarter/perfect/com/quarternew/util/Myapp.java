package quarter.perfect.com.quarternew.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2018/4/6.
 */

public class Myapp extends Application{
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("你猜8989", "onCreate: ");
       /* context=getApplicationContext();
        Log.d("你猜8989", "onCreate: "+context);*/

    }
}
