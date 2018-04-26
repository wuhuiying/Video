package com.example.xiaohe.base.presenter;

import com.example.xiaohe.base.MainActivity;
import com.example.xiaohe.base.kiss.MyPresenterKiss;
import com.example.xiaohe.base.model.MyActivityModel;
import com.example.xiaohe.base.model.MyModel;

/**
 * Created by xiaohe on 2018/4/13.
 */

public class MyAcitivityPresenter extends BaseMvpPresenter<MainActivity> implements MyPresenterKiss{
    private MainActivity mainActivity;

    public MyAcitivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public void getDataURL(String url){
        MyActivityModel myActivityModel = new MyActivityModel(this);
        myActivityModel.getFragmentDataURL(url);
    }

    @Override
    public void getModelData(String json) {
        mainActivity.getPresenterData(json);
    }
}
