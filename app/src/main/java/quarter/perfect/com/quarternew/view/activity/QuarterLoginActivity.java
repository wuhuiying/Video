package quarter.perfect.com.quarternew.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import quarter.perfect.com.quarternew.bean.QuarterLoginBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.presenter.QuarterLoginPresenter;
import quarter.perfect.com.quarternew.util.VerifyUtil;


public class QuarterLoginActivity extends BaseMvpActivity<QuarterLoginActivity,QuarterLoginPresenter> implements QuarterJieKou.QuarterLogin_V,View.OnClickListener{
    private ImageView quarter_login_iv_return;
    private TextView quarter_login_tv_zhuce;
    private EditText quarter_login_et_zhanghao;
    private View vw;
    private EditText quarter_login_et_pass;
    private LinearLayout ll_edit;
    private Button quarter_login_bt_login;
    private TextView tv_forget;
    private TextView tv_tourist;
    private QuarterLoginPresenter quarterLoginPresenter;
    private SharedPreferences quarterLogin;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_login);
        initView();
        quarterLogin = getSharedPreferences("quarterLogin", 0);
        cancelTitle();
    }

    @Override
    public QuarterLoginPresenter initPresenter() {
        //实例化P层
        quarterLoginPresenter = new QuarterLoginPresenter(this);
        return quarterLoginPresenter;
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


    private void initView() {
        quarter_login_iv_return = (ImageView) findViewById(R.id.quarter_login_iv_return);
        quarter_login_tv_zhuce = (TextView) findViewById(R.id.quarter_login_tv_zhuce);
        //用户帐号
        quarter_login_et_zhanghao = (EditText) findViewById(R.id.quarter_login_et_zhanghao);
        vw = (View) findViewById(R.id.vw);
        //用户密码
        quarter_login_et_pass = (EditText) findViewById(R.id.quarter_login_et_pass);
        ll_edit = (LinearLayout) findViewById(R.id.ll_edit);
        quarter_login_bt_login = (Button) findViewById(R.id.quarter_login_bt_login);
        //忘记密码
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        //游客登录
        tv_tourist = (TextView) findViewById(R.id.tv_tourist);
        //初始化点击事件
        quarter_login_bt_login.setOnClickListener(this);
        quarter_login_tv_zhuce.setOnClickListener(this);
        quarter_login_iv_return.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
        tv_tourist.setOnClickListener(this);
    }

    @Override
    public void Onsuccess(QuarterLoginBean quarterLoginBean) {
        if(quarterLoginBean.getMsg().equals("登录成功")){
            Log.d("zzz",quarterLoginBean.getMsg()+"");
            Toast.makeText(QuarterLoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            edit = quarterLogin.edit();
            edit.putString("uid",quarterLoginBean.getData().getUid()+"");
            edit.putString("appkey",quarterLoginBean.getData().getAppkey());
            edit.putString("mobile",quarterLoginBean.getData().getMobile());
            edit.putString("password",quarterLoginBean.getData().getPassword());
            edit.putString("token",quarterLoginBean.getData().getToken());
            edit.putString("username",quarterLoginBean.getData().getUsername());
            edit.commit();
            Intent intent=new Intent(QuarterLoginActivity.this,HomeActivity.class);
            intent.putExtra("token",quarterLoginBean.getData().getToken());
            Log.i("why1111",QuarterLoginActivity.this+"11111");

            intent.putExtra("uid",quarterLoginBean.getData().getUid()+"");
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(QuarterLoginActivity.this,"登录信息不正确",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quarter_login_bt_login:
                String zhanghao = quarter_login_et_zhanghao.getText().toString();
                String pwd = quarter_login_et_pass.getText().toString();
                boolean mobile = VerifyUtil.isMobile(zhanghao);
                boolean password = VerifyUtil.verifyLength(pwd);
                if(zhanghao.isEmpty()){
                    Toast.makeText(QuarterLoginActivity.this,"登录帐号不能为空",Toast.LENGTH_SHORT).show();
                }else if (pwd.isEmpty()){
                    Toast.makeText(QuarterLoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();

                }else {
                    quarterLoginPresenter.getLoginData(zhanghao,pwd);
                }


                break;
            case R.id.quarter_login_tv_zhuce:
                Intent intent1=new Intent(QuarterLoginActivity.this,QuarterRegActivity.class);
                startActivity(intent1);
                break;
            case R.id.quarter_login_iv_return:
                finish();
                break;
            case  R.id.tv_forget:

                break;
            case  R.id.tv_tourist:
                Intent intent2=new Intent(QuarterLoginActivity.this,HomeActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
