package com.cn.bonc.renderEngine.nashorn;

import com.cn.bonc.renderEngine.context.RenderingContext;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/10/21
 *@desc RhinoReactEnginer
 */
public class RhinoReactEnginer {

    private ThreadLocal<NashornScriptEngine> engineHolder = new ThreadLocal<NashornScriptEngine>() {
        @Override
        protected NashornScriptEngine initialValue() {
            NashornScriptEngine nashorn = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");

            try {
                nashorn.eval(read("static/js/main.3f697ea7.js"));
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }
            return nashorn;
        }
    };

    public String render(Map<String, Object> state, RenderingContext context) {
        try {
            Object html = engineHolder.get().invokeFunction("ssr", state, context);
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("what a pity, the server side render is failed.", e);
        }
    }

    public Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
