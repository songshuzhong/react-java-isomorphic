package com.bonc.epm.ui.renderEngine.engines;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/10/21
 *@desc RhinoReactEnginer
 */
public abstract class ReactAbstractEngine implements ReactEngineTool {

    /**
     * @param path
     * @return polyfill.js or render.js
     * @throws IOException
     */
    public String readDynamicJs(String path) throws IOException {
        return read(path);
    }

    /**
     * @param mainJsPath
     * @return main[hash].js
     * @throws IOException
     */
    public String readMainJs(String mainJsPath) throws IOException{
        String path = getMainJsPath(mainJsPath);
        return read(path);
    }

    /**
     * @param path
     * @return the path of main[hash].js
     * @throws IOException
     */
    public String getMainJsPath(String path) throws IOException{
        String assetMainifest = read(path);

        JSONObject jsonObject = JSON.parseObject(assetMainifest);
        return jsonObject.get("main.js").toString();
    }

    /**
     * @param path
     * @return js file
     * @throws IOException
     */
    public String read(String path) throws IOException{
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

}
