package com.bonc.epm.ui.renderEngine.engines;

import com.bonc.epm.ui.renderEngine.context.RenderingContext;
import com.bonc.epm.ui.renderEngine.engines.j2v8.J2V8ReactEnginer;
import com.bonc.epm.ui.renderEngine.engines.nashorn.RhinoReactEnginer;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/30
 *@desc ReactEngineProxy
 */
public class ReactEngineProxy {
    private ReactEngineTool reactEngineTool;

    public ReactEngineProxy(String name) {
        if (name.equals("j2v8")) {
            reactEngineTool = new J2V8ReactEnginer();
        } else if (name.equals("nashorn")) {
            reactEngineTool = new RhinoReactEnginer();
        } else {
            reactEngineTool = new J2V8ReactEnginer();
        }
    }

    public String render(String jsonModel, RenderingContext routerCtx) throws Exception {
        return this.reactEngineTool.render(jsonModel, routerCtx);
    }
}
