package com.example.xiaohe.base.baseview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaohe.base.kiss.MyFragmentKiss;
import com.example.xiaohe.base.presenter.MyPresenter;

/**
 * Created by xiaohe on 2018/4/13.
 */

public class MyFragment extends BaseMvpFragment<MyFragment,MyPresenter> implements MyFragmentKiss {

    private MyPresenter myPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getData();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public void getData(){
        myPresenter.getDataURL("https://www.zhaoapi.cn/ad/getAd");
    }

    @Override
    public MyPresenter initPresenter() {
        myPresenter = new MyPresenter(this);
        return myPresenter;
    }

    @Override
    public void getPresenterData(String json) {
        Log.i("jiba",json);
    }
}
