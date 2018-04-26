package quarter.perfect.com.quarternew.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import quarter.perfect.com.quarternew.bean.QuarterRegBean;
import quarter.perfect.com.quarternew.presenter.QuarterRegPresenter;
import quarter.perfect.com.quarternew.util.QuarterApi;


/**
 * Created by 小慧莹 on 2018/4/17.
 */

public class QuarterRegModel {
    private QuarterRegPresenter quarterRegPresenter;

    public QuarterRegModel(QuarterRegPresenter quarterRegPresenter) {

        this.quarterRegPresenter = quarterRegPresenter;
    }

    public void getRegData(String zhanghao, String pwd) {

        Map<String,String> map=new HashMap<>();
        map.put("mobile",zhanghao);
        map.put("password",pwd);
        map.put("regType","0");
        map.put("uid","13563");
        RetrofitAndOkhttpAndRxAndriodUtil.get(QuarterApi.REG_URL,map)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        QuarterRegBean regBean=new Gson().fromJson(result,QuarterRegBean.class);
                        Log.e("result",regBean.getMsg());
                        quarterRegPresenter.Onsuccess(regBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG","++++++++++++++++++++==============");
                    }
                });

    }
}
