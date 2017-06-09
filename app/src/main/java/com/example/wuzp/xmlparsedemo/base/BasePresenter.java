package com.example.wuzp.xmlparsedemo.base;

/**
 * Created by wuzp on 2017/6/9.
 * Mvp 结构的P层
 */
public class BasePresenter<V extends BaseView> {
    private V view;

    public BasePresenter(V v){
        this.view = v;
    }

    protected void addPresenter(V v){
        attachView(v);
    }

    public void attachView(V view){
        this.view = view;
    }

    public void detchView(){
        this.view = null;
    }

}
