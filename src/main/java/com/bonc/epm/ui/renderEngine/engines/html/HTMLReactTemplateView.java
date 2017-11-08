package com.bonc.epm.ui.renderEngine.engines.html;

import com.bonc.epm.ui.renderEngine.context.RenderingContext;
import com.bonc.epm.ui.renderEngine.engines.ReactAbstractEngine;

import java.io.IOException;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/11/08
 *@desc HTMLReactTemplateView
 */
public class HTMLReactTemplateView extends ReactAbstractEngine {

    private static String templateCache = null;

    private String getTemplate() throws IOException {
        if (templateCache == null) {
            templateCache = getResourceAsString("static/views/index.html");
        }

        return templateCache;
    }

    @Override
    public String render(String jsonModel, RenderingContext routerCtx) throws Exception {
        String contextPath = routerCtx.getContext().getContextPath();
        String view = routerCtx.getLocation();

        jsonModel = java.util.regex.Matcher.quoteReplacement(jsonModel);
        contextPath = java.util.regex.Matcher.quoteReplacement(contextPath);
        view = java.util.regex.Matcher.quoteReplacement(view);

        String html = getTemplate()
                .replaceAll("#CONTEXT_PATH_PLACEHOLDER#", contextPath)
                .replaceAll("#MODEL_JSON_PLACEHOLDER#", jsonModel)
                .replaceAll("#VIEW_NAME_PLACEHOLDER#", view);
        return html;
    }
}
