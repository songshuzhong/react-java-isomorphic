package com.bonc.epm.ui.renderEngine.context;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc CharResponseWrapper
 */
public class RenderingContext {
    private final AppContext context;

    private final String basename;

    private final String location;

    public RenderingContext(AppContext context, String basename, String location) {
        this.context = context;
        this.basename = basename;
        this.location = location;
    }

    public AppContext getContext() {
        return context;
    }

    public String getBasename() {
        return basename;
    }

    public String getLocation() {
        return location;
    }
}
