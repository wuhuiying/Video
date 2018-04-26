package quarter.perfect.com.quarternew.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by xiaohe on 2018/4/19.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
