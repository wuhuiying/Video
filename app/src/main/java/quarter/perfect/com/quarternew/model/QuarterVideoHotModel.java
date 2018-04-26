package quarter.perfect.com.quarternew.model;

import android.content.Context;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import quarter.perfect.com.quarternew.bean.QuarterVideoHotBean;
import quarter.perfect.com.quarternew.presenter.QuarterVideoHotPresenter;
import quarter.perfect.com.quarternew.util.QuarterApi;


/**
 * Created by dell on 2018/4/18.
 */

public class QuarterVideoHotModel {
    private QuarterVideoHotPresenter quarterVideoHotPresenter;
    private Context context;

    public QuarterVideoHotModel(QuarterVideoHotPresenter quarterVideoHotPresenter, Context context) {
        this.quarterVideoHotPresenter = quarterVideoHotPresenter;
        this.context = context;
    }

    public void getData(int page){
        Map<String,String> map=new HashMap<>();
        map.put("token","、");
        map.put("appVersion","101");
        RetrofitAndOkhttpAndRxAndriodUtil.get(QuarterApi.VideoHot_URL,map)

                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson=new Gson();
                        QuarterVideoHotBean quarterVideoHotBean = gson.fromJson(result, QuarterVideoHotBean.class);
                    quarterVideoHotPresenter.Onsuccess(quarterVideoHotBean);
                    }
                });
    }

}
