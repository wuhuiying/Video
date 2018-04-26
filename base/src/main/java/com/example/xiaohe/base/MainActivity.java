package com.example.xiaohe.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.xiaohe.base.baseview.BaseMvpActivity;
import com.example.xiaohe.base.baseview.MyFragment;
import com.example.xiaohe.base.kiss.MyFragmentKiss;
import com.example.xiaohe.base.presenter.MyAcitivityPresenter;
import com.example.xiaohe.base.presenter.MyPresenter;

public class MainActivity extends BaseMvpActivity<MainActivity,MyAcitivityPresenter> implements MyFragmentKiss {

    private FrameLayout my_fragment;
    private MyAcitivityPresenter myAcitivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public MyAcitivityPresenter initPresenter() {
        myAcitivityPresenter = new MyAcitivityPresenter(this);
        return myAcitivityPresenter;
    }

    private void initView() {
        my_fragment = (FrameLayout) findViewById(R.id.my_fragment);
    }

    @Override
    public void getPresenterData(String json) {
        Log.i("goudiao",json);
    }
}
