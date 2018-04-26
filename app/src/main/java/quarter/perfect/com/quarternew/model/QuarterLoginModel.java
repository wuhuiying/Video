package quarter.perfect.com.quarternew.model;

import android.util.Log;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import quarter.perfect.com.quarternew.bean.QuarterLoginBean;
import quarter.perfect.com.quarternew.presenter.QuarterLoginPresenter;
import quarter.perfect.com.quarternew.util.QuarterApi;


/**
 * Created by 小慧莹 on 2018/4/17.
 */

public class QuarterLoginModel {
    private QuarterLoginPresenter quarterLoginPresenter;

    public QuarterLoginModel(QuarterLoginPresenter quarterLoginPresenter) {

        this.quarterLoginPresenter = quarterLoginPresenter;
    }

    public void getLoginData(String zhanghao, String pwd) {
        Map<String,String> map=new HashMap<>();
        map.put("mobile",zhanghao);
        map.put("password",pwd);
        map.put("token","");

        RetrofitAndOkhttpAndRxAndriodUtil.get(QuarterApi.LOGIN_URL,map)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {

                        Log.i("onSuccess", "onSuccess: "+result);
                        QuarterLoginBean loginBean=new Gson().fromJson(result,QuarterLoginBean.class);
                        quarterLoginPresenter.Onsuccess(loginBean);

                    }
                });

    }
}
