package quarter.perfect.com.quarternew.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.Map;

import quarter.perfect.com.quarternew.R;


public class QuarterLeadActivity extends AutoLayoutActivity implements View.OnClickListener {
    private ImageButton quarter_ib_weixin_login;
    private ImageButton quarter_ib_qq_login;
    private TextView quarter_id_qt_login;
    private UMShareAPI umShareAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_lead);
        cancelTitle();
        initView();

    }

    /**
     *控件
     */
    private void initView() {
        quarter_ib_weixin_login = (ImageButton) findViewById(R.id.quarter_ib_weixin_login);
        quarter_ib_qq_login = (ImageButton) findViewById(R.id.quarter_ib_qq_login);
        quarter_id_qt_login = (TextView) findViewById(R.id.quarter_id_qt_login);

        quarter_ib_weixin_login.setOnClickListener(this);
        quarter_ib_qq_login.setOnClickListener(this);
        quarter_id_qt_login.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quarter_ib_weixin_login:
                umShareAPI.getPlatformInfo(QuarterLeadActivity.this, SHARE_MEDIA.WEIXIN, authListener);

                break;
            case R.id.quarter_ib_qq_login:
                Toast.makeText(QuarterLeadActivity.this,"wsjdnfknj",Toast.LENGTH_SHORT).show();
                umShareAPI = UMShareAPI.get(this);
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.quarter_id_qt_login:
                Intent intent=new Intent(QuarterLeadActivity.this,QuarterLoginActivity.class);
                startActivity(intent);
                break;
        }
    }
    UMAuthListener authListener = new UMAuthListener() {
      /*  *//**//**
         * @desc 授权开始的回调
         * @param platform 平台名称
         *//**//**/
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

   /*     *//**//**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         *//**//**/
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(QuarterLeadActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

   /*     *//**//**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         *//**//**/
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(QuarterLeadActivity.this, "失败：" + t.getMessage(),                                  Toast.LENGTH_LONG).show();
        }

      /*  *//**//**
         * @desc 授权取消的回调
         *
         *//**//**/
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(QuarterLeadActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
