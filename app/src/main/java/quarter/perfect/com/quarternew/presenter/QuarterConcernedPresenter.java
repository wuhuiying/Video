package quarter.perfect.com.quarternew.presenter;

import quarter.perfect.com.quarternew.bean.QuarterHotBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.model.QuarterConcernedModel;
import quarter.perfect.com.quarternew.view.QuarterConcernedFragment;

/**
 * Created by Administrator on 2018/4/19.
 */

public class QuarterConcernedPresenter implements QuarterJieKou.QuarterHot_P {
    QuarterConcernedFragment quarterConcernedFragment;
    private final QuarterConcernedModel quarterConcernedModel;

    public QuarterConcernedPresenter(QuarterConcernedFragment quarterConcernedFragment) {
        this.quarterConcernedFragment=quarterConcernedFragment;
        quarterConcernedModel = new QuarterConcernedModel(this);
    }

    public void getHotData() {
        quarterConcernedModel.getHotData();
    }

    @Override
    public void Onsuccess(QuarterHotBean quarterHotBean) {
        quarterConcernedFragment.Onsuccess(quarterHotBean);
    }
}
