package com.example.xiaohe.base.model;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.kiss.MyModelKiss;
import com.example.xiaohe.base.presenter.MyAcitivityPresenter;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;

/**
 * Created by xiaohe on 2018/4/13.
 */

public class MyActivityModel implements MyModelKiss {
    private MyAcitivityPresenter myAcitivityPresenter;

    public MyActivityModel(MyAcitivityPresenter myAcitivityPresenter) {
        this.myAcitivityPresenter = myAcitivityPresenter;
    }

    @Override
    public void getFragmentDataURL(String url) {
        RetrofitAndOkhttpAndRxAndriodUtil.get(url)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        myAcitivityPresenter.getModelData(result);
                    }
                });
    }
}
