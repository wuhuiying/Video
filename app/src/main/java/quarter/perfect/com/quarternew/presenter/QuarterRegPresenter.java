package quarter.perfect.com.quarternew.presenter;


import com.example.xiaohe.base.presenter.BaseMvpPresenter;

import quarter.perfect.com.quarternew.bean.QuarterRegBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.model.QuarterRegModel;
import quarter.perfect.com.quarternew.view.activity.QuarterRegActivity;

/**
 * Created by 小慧莹 on 2018/4/17.
 */

public class QuarterRegPresenter extends BaseMvpPresenter<QuarterRegActivity> implements QuarterJieKou.QuarterReg_P {
    private QuarterRegActivity quarterRegActivity;
    private final QuarterRegModel quarterRegModel;

    public QuarterRegPresenter(QuarterRegActivity quarterRegisterActivity) {

        this.quarterRegActivity = quarterRegisterActivity;
        quarterRegModel = new QuarterRegModel(this);
    }

    @Override
    public void Onsuccess(QuarterRegBean quarterRegBean) {
        quarterRegActivity.Onsuccess(quarterRegBean);


    }

    public void getRegData(String zhanghao, String pwd) {
        quarterRegModel.getRegData(zhanghao,pwd);

    }
}
