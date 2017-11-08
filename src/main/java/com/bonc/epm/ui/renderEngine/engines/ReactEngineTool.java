package com.bonc.epm.ui.renderEngine.engines;

import com.bonc.epm.ui.renderEngine.context.RenderingContext;

import java.io.IOException;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/30
 *@desc ReactEngineTool
 */
public interface ReactEngineTool {

    String render(String jsonModel, RenderingContext routerCtx) throws Exception;

    String readDynamicJs(String path) throws IOException;

    String readMainJs(String mainJsPath) throws IOException;

    String getMainJsPath(String path) throws IOException;

    String getResourceAsString(String path) throws IOException;

}
