package com.bonc.epm.ui.renderEngine.engines;

import com.bonc.epm.ui.renderEngine.context.RenderingContext;
import com.bonc.epm.ui.renderEngine.engines.html.HTMLReactTemplateView;
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

    public ReactEngineProxy(String reactConfig) {
        switch (reactConfig) {
            case "j2v8":
                reactEngineTool = new J2V8ReactEnginer();
                break;
            case "nashorn":
                reactEngineTool = new RhinoReactEnginer();
                break;
            case "html":
                reactEngineTool = new HTMLReactTemplateView(reactConfig);
                break;
            default:
                reactEngineTool = new HTMLReactTemplateView(reactConfig);
                break;
        }
    }

    public String render(String jsonModel, RenderingContext routerCtx) throws Exception {
        return this.reactEngineTool.render(jsonModel, routerCtx);
    }
}
