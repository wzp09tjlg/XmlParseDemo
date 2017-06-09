package com.example.wuzp.xmlparsedemo.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wuzp on 2017/6/9.
 */

public abstract class BindingFragment<B extends ViewDataBinding,P extends BasePresenter> extends BaseFragment {
    private B binding;
    private P mvpPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mvpPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,createLayout(),null,false);
        return binding.getRoot();
    }

    public void onViewCreate(){}

    @Override
    public void onDetach() {
        super.onDetach();
        if(mvpPresenter != null){
            mvpPresenter.detchView();
        }
    }

    public abstract P createPresenter();

    public abstract int createLayout();

}
