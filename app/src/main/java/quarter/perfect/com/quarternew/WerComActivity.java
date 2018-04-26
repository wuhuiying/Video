package quarter.perfect.com.quarternew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaohe.base.MainActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hjm.bottomtabbar.BottomTabBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zhy.autolayout.AutoLayoutActivity;

import quarter.perfect.com.quarternew.view.QuarterAnEpisodeFragment;
import quarter.perfect.com.quarternew.view.QuarterRecommendFragment;
import quarter.perfect.com.quarternew.view.QuarterVideoFragment;

public class WerComActivity extends AutoLayoutActivity {


    private SimpleDraweeView quarter_wercome_touxiang_sdv;
    private TextView quarter_wercome_title_tv;
    private BottomTabBar quarter_wercome_bt;
    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wer_com);
        cancelTitle();
        initView();
        quarter_wercome_title_tv.setText("推荐");
        quarter_wercome_bt.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("推荐",R.drawable.raw_1500085367, QuarterRecommendFragment.class)
                .addTabItem("段子",R.drawable.raw_1500085899, QuarterAnEpisodeFragment.class)
                .addTabItem("视频",R.drawable.raw_1500086067, QuarterVideoFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        quarter_wercome_title_tv.setText(name);
                        SharedPreferences settings = getSharedPreferences("login_ny", 0);
                        boolean login = settings.getBoolean("login", true);
                        Log.i("jiba","-------------------------"+login);
                        if(position!=0&&!login){

                        }
                    }
                });
        cela();

    }

    private void initView() {
        quarter_wercome_touxiang_sdv = (SimpleDraweeView) findViewById(R.id.quarter_wercome_touxiang_sdv);
        quarter_wercome_title_tv = (TextView) findViewById(R.id.quarter_wercome_title_tv);
        quarter_wercome_bt = (BottomTabBar) findViewById(R.id.quarter_wercome_bt);
        quarter_wercome_touxiang_sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void cela(){
        menu = new SlidingMenu(this);
        //设置侧滑的方向.左侧
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        // 设置滑动完剩余的宽度
        menu.setBehindOffset(200);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        //绑定
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.quarter_sliding);
    }
    private void cancelTitle() {
        //取消标题栏
        getSupportActionBar().hide();
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
