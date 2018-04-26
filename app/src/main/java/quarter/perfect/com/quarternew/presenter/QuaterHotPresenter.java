package quarter.perfect.com.quarternew.presenter;

import com.example.xiaohe.base.presenter.BaseMvpPresenter;

import quarter.perfect.com.quarternew.bean.QuarterHotBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.model.QuaterHotModel;
import quarter.perfect.com.quarternew.view.QuarterHotFragment;

/**
 * Created by Administrator on 2018/4/17.
 */

public class QuaterHotPresenter extends BaseMvpPresenter<QuarterHotFragment> implements QuarterJieKou.QuarterHot_P{

    private final QuaterHotModel quaterHotModel;
    QuarterHotFragment quaterHotFragment;
    public QuaterHotPresenter(QuarterHotFragment quaterHotFragment) {
        this.quaterHotFragment=quaterHotFragment;
        quaterHotModel = new QuaterHotModel(this);
    }

    public void getHotData() {
        quaterHotModel.getHotData();
    }

    @Override
    public void Onsuccess(QuarterHotBean quarterHotBean) {
        quaterHotFragment.Onsuccess(quarterHotBean);
    }
}
