package com.example.wuzp.xmlparsedemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wuzp on 2017/6/9.
 * Activity 的基类
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addFragment(int containerViewId, Fragment fragment) {
        addFragment(containerViewId, fragment, false);
    }

    public void addFragment(int containerViewId, Fragment fragment, boolean addBackStack) {
        FragmentTransaction fragmentTransaction = null;
        fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, fragment.getClass().getSimpleName());
        if (addBackStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
