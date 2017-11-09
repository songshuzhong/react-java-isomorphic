package com.bonc.epm.ui.renderEngine.engines;

import com.bonc.epm.ui.renderEngine.context.RenderingContext;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/30
 *@desc ReactEngineTool
 */
public interface ReactEngineTool {

    String render(String jsonModel, RenderingContext routerCtx);

    String readDynamicJs(String path);

    String readMainJs(String mainJsPath);

    String getMainJsPath(String path);

    String getResourceAsString(String path);

}
