package quarter.perfect.com.quarternew.presenter;


import com.example.xiaohe.base.presenter.BaseMvpPresenter;

import quarter.perfect.com.quarternew.bean.QuarterLoginBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.model.QuarterLoginModel;
import quarter.perfect.com.quarternew.view.activity.QuarterLoginActivity;

/**
 * Created by 小慧莹 on 2018/4/17.
 */

public class QuarterLoginPresenter extends BaseMvpPresenter<QuarterLoginActivity> implements QuarterJieKou.QuarterLogin_P {
    private QuarterLoginActivity quarterLoginActivity;
    private final QuarterLoginModel quarterLoginModel;

    public QuarterLoginPresenter(QuarterLoginActivity quarterLoginActivity) {

        this.quarterLoginActivity = quarterLoginActivity;
        quarterLoginModel = new QuarterLoginModel(this);
    }

    @Override
    public void Onsuccess(QuarterLoginBean quarterLoginBean) {
        quarterLoginActivity.Onsuccess(quarterLoginBean);

    }

    public void getLoginData(String zhanghao, String pwd) {
        quarterLoginModel.getLoginData(zhanghao,pwd);
    }
}
