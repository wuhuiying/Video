package quarter.perfect.com.quarternew.presenter;

import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.model.QuarterHotCarouselModel;
import quarter.perfect.com.quarternew.view.QuarterHotFragment;

/**
 * Created by Administrator on 2018/4/19.
 */

public class QuarterHotCarouselPresenter implements QuarterJieKou.QuarterCarousel_P{

    private final QuarterHotCarouselModel quarterCarouselModel;
    QuarterHotFragment quarterHotFragment;
    public QuarterHotCarouselPresenter(QuarterHotFragment quarterHotFragment) {
        this.quarterHotFragment=quarterHotFragment;
        quarterCarouselModel = new QuarterHotCarouselModel(this);
    }

    public void getCarouselData() {
        quarterCarouselModel.getCarouselData();
    }

    @Override
    public void Onsuccess(QuarterCarouselBean quarterCarouselBean) {
        quarterHotFragment.Onsuccess(quarterCarouselBean);
    }
}
