package com.example.xiaohe.base.presenter;

import com.example.xiaohe.base.baseview.MyFragment;
import com.example.xiaohe.base.kiss.MyPresenterKiss;
import com.example.xiaohe.base.model.MyModel;

/**
 * Created by xiaohe on 2018/4/13.
 */

public class MyPresenter extends BaseMvpPresenter<MyFragment> implements MyPresenterKiss {
    private MyFragment myFragment;

    public MyPresenter(MyFragment myFragment) {
        this.myFragment = myFragment;
    }

    public void getDataURL(String url){
        MyModel myModel = new MyModel(this);
        myModel.getFragmentDataURL(url);
    }

    @Override
    public void getModelData(String json) {
        myFragment.getPresenterData(json);
    }
}
