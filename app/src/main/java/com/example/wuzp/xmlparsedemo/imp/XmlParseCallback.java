package com.example.wuzp.xmlparsedemo.imp;

import com.example.wuzp.xmlparsedemo.model.NodeBean;

import java.util.List;

/**
 * Created by wuzp on 2017/6/9.
 */
public interface XmlParseCallback {
    void parseCallback(List<NodeBean> date);
}
