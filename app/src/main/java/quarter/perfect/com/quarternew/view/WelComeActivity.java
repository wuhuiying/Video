package quarter.perfect.com.quarternew.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLayoutActivity;

import quarter.perfect.com.quarternew.HomeActivity;
import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.view.activity.QuarterLeadActivity;

public class WelComeActivity extends AutoLayoutActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        cancelTitle();
       new Thread(){
           @Override
           public void run() {
               try {
                   sleep(3000);
                   startActivity(new Intent(WelComeActivity.this,QuarterLeadActivity.class));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }.start();
    }

    /**
     * 取消标题栏
     */
    private void cancelTitle() {
        //取消标题栏
        getSupportActionBar().hide();
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
