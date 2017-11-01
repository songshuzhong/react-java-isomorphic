package com.cn.bonc.renderEngine.context;

/**
 * Created by Administrator on 2017/10/27.
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
