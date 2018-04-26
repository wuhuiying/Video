package quarter.perfect.com.quarternew.presenter;

import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.model.QuarterConcernedCarouselModel;
import quarter.perfect.com.quarternew.view.QuarterConcernedFragment;

/**
 * Created by Administrator on 2018/4/19.
 */

public class QuarterConcernedCarouselPresenter implements QuarterJieKou.QuarterCarousel_P {
    QuarterConcernedFragment quarterConcernedFragment;
    private final QuarterConcernedCarouselModel quarterConcernedCarouselModel;

    public QuarterConcernedCarouselPresenter(QuarterConcernedFragment quarterConcernedFragment) {
        this.quarterConcernedFragment=quarterConcernedFragment;
        quarterConcernedCarouselModel = new QuarterConcernedCarouselModel(this);
    }

    public void getData() {
        quarterConcernedCarouselModel.getData();
    }

    @Override
    public void Onsuccess(QuarterCarouselBean quarterCarouselBean) {
        quarterConcernedFragment.Onsuccess(quarterCarouselBean);
    }
}
