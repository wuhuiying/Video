package quarter.perfect.com.quarternew.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.zhy.autolayout.AutoLayoutActivity;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.util.QuarterRewritePopwindow;


public class QuarterDetailActivity extends AutoLayoutActivity implements View.OnClickListener{

    PlayerView play;
    String url = "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    private ImageView detail_return;
    private ImageView detail_like;
    boolean state=true;
    private ImageView detail_broken;
    private ImageView quarter_detail_share;
    private QuarterRewritePopwindow mPopwindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_detail);
        cancelTitle();
        detail_return = findViewById(R.id.quarter_detail_return);
        detail_like = findViewById(R.id.quarter_detail_like);
        detail_broken = findViewById(R.id.quarter_detail_broken);
        quarter_detail_share = findViewById(R.id.quarter_detail_share);
        quarter_detail_share.setOnClickListener(this);
        findViewById(R.id.quarter_detail_portrait);
        play = new PlayerView(this)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(url);
        play.startPlay();

        detail_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

     detail_broken.setOnClickListener(this);
     detail_like.setOnClickListener(this);


    }
    private void cancelTitle() {
        //取消标题栏
        getSupportActionBar().hide();
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    protected void onStop() {
        super.onStop();
        play.stopPlay();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quarter_detail_like:
                if(state){
                    detail_like.setImageResource(R.drawable.raw_1499933215);
                    state=false;
                }else{
                    detail_like.setImageResource(R.drawable.raw_1499933216);
                    state=true;
                }
                break;
            case R.id.quarter_detail_broken:
                if(state){
                    detail_broken.setImageResource(R.drawable.raw_1499933218);
                    state=false;
                }else{
                    detail_broken.setImageResource(R.drawable.raw_1499933217);
                    state=true;
                }
                break;
            case R.id.quarter_detail_share:
                mPopwindow = new QuarterRewritePopwindow(QuarterDetailActivity.this, itemsOnClick);
                mPopwindow.showAtLocation(v,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;

        }
    }


    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            mPopwindow.dismiss();
            mPopwindow.backgroundAlpha(QuarterDetailActivity.this, 1f);
            switch (v.getId()) {
                case R.id.weixinghaoyou:
                    Toast.makeText(QuarterDetailActivity.this, "微信好友", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pengyouquan:
                    Toast.makeText(QuarterDetailActivity.this, "朋友圈", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.qqhaoyou:
                    Toast.makeText(QuarterDetailActivity.this, "QQ好友", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.qqkongjian:
                    Toast.makeText(QuarterDetailActivity.this, "QQ空间", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    };
}
