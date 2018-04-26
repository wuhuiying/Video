package quarter.perfect.com.quarternew.model;

import android.support.annotation.NonNull;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;
import com.google.gson.Gson;

import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;
import quarter.perfect.com.quarternew.presenter.QuarterHotCarouselPresenter;
import quarter.perfect.com.quarternew.util.QuarterApi;

/**
 * Created by Administrator on 2018/4/19.
 */

public class QuarterHotCarouselModel {
    QuarterHotCarouselPresenter quarterCarouselPresenter;

    public QuarterHotCarouselModel(QuarterHotCarouselPresenter quarterCarouselPresenter) {
        this.quarterCarouselPresenter = quarterCarouselPresenter;
    }

    public void getCarouselData() {
        RetrofitAndOkhttpAndRxAndriodUtil.get(QuarterApi.Carousel_URL)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        QuarterCarouselBean quarterCarouselBean = new Gson().fromJson(result, QuarterCarouselBean.class);
                        quarterCarouselPresenter.Onsuccess(quarterCarouselBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
