package com.example.wuzp.xmlparsedemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by wuzp on 2017/6/9.
 * Mvp 的Activity 基类
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvpPresenter = createPresenter();
    }

    public abstract P createPresenter();

}
