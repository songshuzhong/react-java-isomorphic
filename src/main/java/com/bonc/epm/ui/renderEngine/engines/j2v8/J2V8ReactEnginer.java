package com.bonc.epm.ui.renderEngine.engines.j2v8;

import com.bonc.epm.ui.renderEngine.context.RenderingContext;
import com.bonc.epm.ui.renderEngine.engines.ReactAbstractEngine;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/30
 *@desc J2V8ReactEnginer
 */
public class J2V8ReactEnginer extends ReactAbstractEngine{

    private V8 createRuntime(List<V8Value> runtimeObjects, RenderingContext routerCtx) {
        V8 runtime = V8.createV8Runtime();
        initRuntime(runtime, runtimeObjects, routerCtx);
        return runtime;
    }

    private void initRuntime(V8 runtime, List<V8Value> runtimeObject, RenderingContext routerCtx) {
        Console.registerAsJavaMethod(runtime);

        String __CONTEXT_PATH__ = "";
        if (routerCtx.getContext().getContextPath() != "") {
            __CONTEXT_PATH__ = routerCtx.getContext().getContextPath().substring(1);
        }

        try {
            executeScript(runtime, runtimeObject, readDynamicJs("static/js/j2v8-polyfill.js"));
            runtime.executeVoidScript("global.__CONTEXT_PATH__ = '" + __CONTEXT_PATH__ + "'");
            executeScript(runtime, runtimeObject, readMainJs("static/asset-manifest.json"));
            executeScript(runtime, runtimeObject, readDynamicJs("static/js/render.js"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeScript(V8 runtime, List<V8Value> runtimeObjects, String script) {
        try {
            Object object = runtime.executeScript(script);

            if (object instanceof V8Value) {
                runtimeObjects.add((V8Value) object);
            }
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Failed to execute script %s", script), e);
        }
    }

    @Override
    public String render(String jsonModel, RenderingContext routerCtx) throws Exception {
        List<V8Value> runtimeObjects = new ArrayList<>();
        V8 runtime = createRuntime(runtimeObjects, routerCtx);

        ObjectMapper mapper = new ObjectMapper();
        String jsonContext = mapper.writeValueAsString(routerCtx);

        Object html = runtime.executeJSFunction("render", jsonModel, jsonContext);
        releaseRuntime(runtime, runtimeObjects);
        return String.valueOf(html);
    }

    private void releaseRuntime(V8 runtime, List<V8Value> runtimeObjects) {
        runtimeObjects.forEach(V8Value::release);
        runtime.getObject("global").release();
        runtime.release();
    }

}