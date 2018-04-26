package quarter.perfect.com.quarternew.model;

import android.support.annotation.NonNull;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import quarter.perfect.com.quarternew.bean.QuarterHotBean;
import quarter.perfect.com.quarternew.presenter.QuaterHotPresenter;
import quarter.perfect.com.quarternew.util.QuarterApi;


/**
 * Created by Administrator on 2018/4/17.
 */

public class QuaterHotModel {
    QuaterHotPresenter quaterHotPresenter;
    public QuaterHotModel(QuaterHotPresenter quaterHotPresenter) {
        this.quaterHotPresenter = quaterHotPresenter;
    }

    public void getHotData() {
        Map<String, String> map=new HashMap<>();
        map.put("type","1");
        map.put("page","10");
        map.put("appVersion","101");
        RetrofitAndOkhttpAndRxAndriodUtil.get(QuarterApi.HOT_URL,map)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        QuarterHotBean quarterHotBean = new Gson().fromJson(result, QuarterHotBean.class);
                        quaterHotPresenter.Onsuccess(quarterHotBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
