package com.example.wuzp.xmlparsedemo.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

/**
 * Created by wuzp on 2017/6/9.
 */

public abstract class BindActivity<B extends ViewDataBinding,P extends BasePresenter> extends BaseActivity {
    protected B binding;
    protected P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        binding = DataBindingUtil.inflate(inflater,createLayout(),null,false);//后边的两个参数是表示
    }

    public abstract P createPresenter();

    public abstract int createLayout();

}
