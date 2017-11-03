package com.bonc.epm.ui.renderEngine.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc ReactEngineFilter
 */
public class CharRequestWrapper extends HttpServletRequestWrapper {

    public CharRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        if (name.equals("Accept")) {
            return "application/json";
        }
        return super.getHeader(name);
    }
}
