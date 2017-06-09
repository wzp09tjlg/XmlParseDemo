package com.example.wuzp.xmlparsedemo.base;

import android.content.Context;

/**
 * Created by wuzp on 2017/6/9.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mvpPresenter = createPresenter();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mvpPresenter != null){
            mvpPresenter.detchView();
        }
    }

    public abstract P createPresenter();
}
