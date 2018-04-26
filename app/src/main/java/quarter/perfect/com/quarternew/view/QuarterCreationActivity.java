package quarter.perfect.com.quarternew.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.autolayout.AutoLayoutActivity;

import quarter.perfect.com.quarternew.R;

public class QuarterCreationActivity extends AutoLayoutActivity implements View.OnClickListener {

    private TextView quarter_creation_return_tv;
    private SimpleDraweeView quarter_craetion_video_sdv;
    private SimpleDraweeView quarter_craetion_duanzi_sdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_creation);
        cancelTitle();
        initView();

    }
    private void cancelTitle() {
        //取消标题栏
        getSupportActionBar().hide();
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initView() {
        quarter_creation_return_tv = (TextView) findViewById(R.id.quarter_creation_return_tv);
        quarter_craetion_video_sdv = (SimpleDraweeView) findViewById(R.id.quarter_craetion_video_sdv);
        quarter_craetion_duanzi_sdv = (SimpleDraweeView) findViewById(R.id.quarter_craetion_duanzi_sdv);
        quarter_craetion_duanzi_sdv.setOnClickListener(this);
        quarter_craetion_video_sdv.setOnClickListener(this);
        quarter_creation_return_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quarter_creation_return_tv:
                finish();
                break;
            case R.id.quarter_craetion_video_sdv:
                startActivity(new Intent(QuarterCreationActivity.this,QuarterVideoRecordActivity.class));
                break;
            case R.id.quarter_craetion_duanzi_sdv:
                startActivity(new Intent(QuarterCreationActivity.this,QuarterCraeteAnEpisodeActivity  .class));
                break;
        }
    }
}
