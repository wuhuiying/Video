package com.example.xiaohe.base.presenter;

/**
 * Created by xiaohe on 2018/4/13.
 */

public class BaseMvpPresenter<T> {
    private T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}
