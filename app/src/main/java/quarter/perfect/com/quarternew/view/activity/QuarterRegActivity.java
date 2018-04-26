package quarter.perfect.com.quarternew.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaohe.base.baseview.BaseMvpActivity;

import quarter.perfect.com.quarternew.HomeActivity;
import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.WerComActivity;
import quarter.perfect.com.quarternew.bean.QuarterRegBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.presenter.QuarterRegPresenter;
import quarter.perfect.com.quarternew.util.VerifyUtil;


public class QuarterRegActivity extends BaseMvpActivity<QuarterRegActivity,QuarterRegPresenter> implements View.OnClickListener,QuarterJieKou.QuarterReg_V {
    private ImageView quarter_register_iv_return;
    private TextView quarter_register_tv_yiyou;
    private EditText quarter_register_et_phone;
    private EditText quarter_register_et_pass;
    private LinearLayout ll_edit2;
    private Button quarter_register_bt_register;
    private TextView tv_tourist2;
    private QuarterRegPresenter quarterRegPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_reg);
        initView();
    }


    private void initView() {
        quarter_register_iv_return = (ImageView) findViewById(R.id.quarter_register_iv_return);
        quarter_register_tv_yiyou = (TextView) findViewById(R.id.quarter_register_tv_yiyou);
        //用户名
        quarter_register_et_phone = (EditText) findViewById(R.id.quarter_register_et_phone);
        //用户密码
        quarter_register_et_pass = (EditText) findViewById(R.id.quarter_register_et_pass);
        ll_edit2 = (LinearLayout) findViewById(R.id.ll_edit2);
        quarter_register_bt_register = (Button) findViewById(R.id.quarter_register_bt_register);
        tv_tourist2 = (TextView) findViewById(R.id.tv_tourist2);
        tv_tourist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuarterRegActivity.this,HomeActivity.class));
            }
        });
        quarter_register_bt_register.setOnClickListener(this);
        quarter_register_iv_return.setOnClickListener(this);
    }


    @Override
    public QuarterRegPresenter initPresenter() {
        quarterRegPresenter = new QuarterRegPresenter(this);
        return quarterRegPresenter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quarter_register_bt_register:
                String zhanghao = quarter_register_et_phone.getText().toString();
                String pwd = quarter_register_et_pass.getText().toString();
                boolean   phone = VerifyUtil.isMobile(zhanghao);
                boolean   password = VerifyUtil.verifyLength(pwd);
                if(phone&&password){
                    quarterRegPresenter.getRegData(zhanghao,pwd);
                }else {
                    Toast.makeText(QuarterRegActivity.this, "帐号或密码不能为空!!", Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(QuarterRegActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.quarter_register_iv_return:
                finish();
                break;
        }
    }
    @Override
    public void Onsuccess(QuarterRegBean quarterRegBean) {
        Log.i("regBean", "Onsuccess: "+quarterRegBean.toString());
        if(quarterRegBean.getCode().equals("0")){
            Toast.makeText(QuarterRegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(QuarterRegActivity.this,"注册失败--"+quarterRegBean.getMsg(),Toast.LENGTH_SHORT).show();
        }



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
