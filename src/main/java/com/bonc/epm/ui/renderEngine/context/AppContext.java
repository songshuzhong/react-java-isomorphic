package com.bonc.epm.ui.renderEngine.context;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc CharResponseWrapper
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
