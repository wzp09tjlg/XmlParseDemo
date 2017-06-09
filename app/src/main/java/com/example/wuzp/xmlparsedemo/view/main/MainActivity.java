package com.example.wuzp.xmlparsedemo.view.main;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wuzp.xmlparsedemo.R;
import com.example.wuzp.xmlparsedemo.base.BindActivity;
import com.example.wuzp.xmlparsedemo.databinding.ActivityMainBinding;
import com.example.wuzp.xmlparsedemo.imp.XmlParseCallback;
import com.example.wuzp.xmlparsedemo.model.NodeBean;
import com.example.wuzp.xmlparsedemo.model.StudentBean;
import com.example.wuzp.xmlparsedemo.utils.SaxHandler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends BindActivity<ActivityMainBinding, MainPresenter> implements
        MainContract.IView, View.OnClickListener {
    private MainViewWrapper viewWrapper = new MainViewWrapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public int createLayout() {
        return R.layout.activity_main;
    }

    private void initView() {
        viewWrapper.addBinding(binding);
        binding.btnSax.setOnClickListener(this);
        binding.btnPull.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sax:
                saxParseXml();
                break;
            case R.id.btn_pull:
                List<StudentBean> date = pullParseXml();
                if(date != null){
                    Log.e("wzp","pull size:" + date.size());
                }else
                    Log.e("wzp","pull size: null");
                break;
        }
    }

    private void saxParseXml() {
        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("nodes.xml");
            SAXParserFactory factory
                    = SAXParserFactory.newInstance();
            SAXParser parse = factory.newSAXParser();
            parse.parse(inputStream, new SaxHandler(callBack));
        } catch (Exception e) {
        }
    }

    private XmlParseCallback callBack = new XmlParseCallback() {
        @Override
        public void parseCallback(List<NodeBean> date) {
            if(date != null){
                Log.e("wzp","sax size:" + date.size());
            }else
                Log.e("wzp","sax size: null");
        }
    };

    private List<StudentBean> pullParseXml() {
        List<StudentBean> nodeBeanList = new ArrayList<>();//java7 中的泛型推断
        StudentBean tempBean = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("students.xml");

            pullParser.setInput(inputStream, "UTF-8");

            int eventType = pullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName = pullParser.getName();
                        switch (tagName) {
                            case "student":
                                tempBean = new StudentBean();
                                tempBean.setId(pullParser.getAttributeValue(0));
                                tempBean.setGroup(pullParser.getAttributeName(1));
                                break;
                            case "name":
                                if (tempBean != null)
                                    tempBean.setName(pullParser.getText());
                                break;
                            case "sex":
                                if (tempBean != null)
                                    tempBean.setSex(pullParser.getText());
                                break;
                            case "age":
                                if (tempBean != null)
                                    tempBean.setAge(pullParser.getText());
                                break;
                            case "email":
                                if (tempBean != null)
                                    tempBean.setEmail(pullParser.getText());
                                break;
                            case "birthday":
                                if (tempBean != null)
                                    tempBean.setBirthday(pullParser.getText());
                                break;
                            case "memo":
                                if (tempBean != null)
                                    tempBean.setMemo(pullParser.getText());
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String name = pullParser.getName();
                        switch (name) {
                            case "student":
                                if (tempBean != null)
                                    nodeBeanList.add(tempBean);
                                break;
                        }
                        break;
                }

                eventType = eventType = pullParser.next();///调用的是next的方法 来获取当前的状态
            }
        } catch (Exception e) {
        }
        return nodeBeanList;
    }
}
