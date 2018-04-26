package quarter.perfect.com.quarternew.presenter;

import android.content.Context;

import com.example.xiaohe.base.presenter.BaseMvpPresenter;

import quarter.perfect.com.quarternew.bean.QuarterVideoHotBean;
import quarter.perfect.com.quarternew.interfaceall.QuarterJieKou;
import quarter.perfect.com.quarternew.model.QuarterVideoHotModel;
import quarter.perfect.com.quarternew.view.QuarterVideoHotFragment;


/**
 * Created by dell on 2018/4/18.
 */

public class QuarterVideoHotPresenter extends BaseMvpPresenter<QuarterVideoHotFragment> implements QuarterJieKou. QuarterVideoHot_P{
      QuarterJieKou.QuarterVideoHot_V quarterVideoHot_v;
      QuarterVideoHotModel quarterVideoHotModel;
      Context context;

    public QuarterVideoHotPresenter(QuarterJieKou.QuarterVideoHot_V quarterVideoHot_v, Context context) {
        this.quarterVideoHot_v = quarterVideoHot_v;
        this.context = context;
        quarterVideoHotModel=new QuarterVideoHotModel(this,context);
    }

    public void getData(int page){
        quarterVideoHotModel.getData(page);
    }

    @Override
    public void Onsuccess(QuarterVideoHotBean quarterVideoHotBean) {
        quarterVideoHot_v.Onsuccess(quarterVideoHotBean);
    }
}
