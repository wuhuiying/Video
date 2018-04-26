package com.example.xiaohe.base.model;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.kiss.MyModelKiss;
import com.example.xiaohe.base.presenter.MyPresenter;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;

/**
 * Created by xiaohe on 2018/4/13.
 */

public class MyModel implements MyModelKiss {
    private MyPresenter myPresenter;

    public MyModel(MyPresenter myPresenter) {
        this.myPresenter = myPresenter;
    }

    @Override
    public void getFragmentDataURL(String url) {
        RetrofitAndOkhttpAndRxAndriodUtil.get(url)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        myPresenter.getModelData(result);
                    }
                });
    }
}
