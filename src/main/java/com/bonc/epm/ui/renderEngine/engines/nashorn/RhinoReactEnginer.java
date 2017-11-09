package com.bonc.epm.ui.renderEngine.engines.nashorn;

import com.bonc.epm.ui.renderEngine.context.RenderingContext;
import com.bonc.epm.ui.renderEngine.engines.ReactAbstractEngine;
import com.bonc.epm.ui.renderEngine.exception.SourceLoaderException;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import java.util.*;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/10/21
 *@desc RhinoReactEnginer
 */
public class RhinoReactEnginer extends ReactAbstractEngine{
    private static final ThreadLocal<Map<Object, ScriptEngine>> enginesHolder = new ThreadLocal<>();
    private volatile ScriptEngineManager scriptEngineManager;

    private ScriptEngine getEngine(RenderingContext routerCtx) {
        Map<Object, ScriptEngine> engines = enginesHolder.get();
        if (engines == null) {
            engines = new HashMap<Object, ScriptEngine>(4);
            enginesHolder.set(engines);
        }
        Object engineKey =  "DEFAULT_REACT_NASHORN_ENGINE";
        ScriptEngine engine = engines.get(engineKey);
        if (engine == null) {
            engine = createEngine(routerCtx);
            engines.put(engineKey, engine);
        }
        return engine;
    }

    private ScriptEngine createEngine(RenderingContext routerCtx) {
        if (this.scriptEngineManager == null) {
            this.scriptEngineManager = new ScriptEngineManager(getClass().getClassLoader());
        }

        ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");
        initEngine(engine, routerCtx);
        return engine;
    }

    private void initEngine(ScriptEngine engine, RenderingContext routerCtx) {
        String __CONTEXT_PATH__ = "";
        if (routerCtx.getContext().getContextPath() != "") {
            __CONTEXT_PATH__ = routerCtx.getContext().getContextPath().substring(1);
        }

        try {
            engine.eval(readDynamicJs("static/js/nashorn-polyfill.js"));
            engine.eval("global.__CONTEXT_PATH__ = '" + __CONTEXT_PATH__ + "'");
            engine.eval(readMainJs("static/asset-manifest.json"));
            engine.eval(readDynamicJs("static/js/render.js"));
        } catch (Throwable e) {
            throw  new SourceLoaderException(String.format("EPM UI JAVA Integration: RhinoReactEngine is faild to execute the main.[hash].js."), e);
        }
    }

    @Override
    public String render(String jsonModel, RenderingContext routerCtx) {
        try {
            ScriptEngine engine = getEngine(routerCtx);
            Invocable invocable = (Invocable) engine;

            ObjectMapper mapper = new ObjectMapper();
            String jsonContext = mapper.writeValueAsString(routerCtx);

            Object html = invocable.invokeFunction("render", jsonModel, jsonContext);

            return String.valueOf(html);
        } catch (JsonProcessingException | NoSuchMethodException | ScriptException e) {
            throw new SourceLoaderException(String.format("EPM UI JAVA Integration: RhinoReactEngine's rendering is faild."), e);
        }
    }

    protected static class EngineKey {

        private final String[] scripts;
        private final String[] renderScripts;
        private final String templateScript;

        public EngineKey(String[] scripts, String[] renderScripts, String templateScript) {
            this.scripts = scripts;
            this.renderScripts = renderScripts;
            this.templateScript = templateScript;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EngineKey)) {
                return false;
            }
            EngineKey otherKey = (EngineKey) other;
            return (Arrays.equals(this.scripts, otherKey.scripts) && Arrays.equals(this.renderScripts, otherKey.renderScripts) && this.templateScript.equals(otherKey.templateScript));
        }

        @Override
        public int hashCode() {
            return ((Arrays.hashCode(this.scripts) * 31 + Arrays.hashCode(this.renderScripts)) * 31 + this.templateScript.hashCode());
        }
    }
}
