package com.cn.bonc.renderEngine.context;

/**
 * Created by Administrator on 2017/10/27.
 */
public class AppContext {
    private final String contextPath;

    public AppContext(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getContextPath() {
        return contextPath;
    }
}
