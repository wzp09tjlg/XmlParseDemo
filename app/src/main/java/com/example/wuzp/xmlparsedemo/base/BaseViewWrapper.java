package com.example.wuzp.xmlparsedemo.base;

import android.databinding.ViewDataBinding;

/**
 * Created by wuzp on 2017/6/9.
 * ViewWrapper 对View的封装
 */
public class BaseViewWrapper<B extends ViewDataBinding> {

    private B binding;

    public BaseViewWrapper(){
        this.binding = null;
    }

    public void addBinding(B binding){
        this.binding = binding;
    }

}
