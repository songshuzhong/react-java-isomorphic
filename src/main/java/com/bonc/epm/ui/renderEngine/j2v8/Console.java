package com.bonc.epm.ui.renderEngine.j2v8;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

/**
 * Created by Administrator on 2017/10/28.
 */
public class Console {
    private static Console console = new Console();

    public void log(final String message) {
        System.out.println("[Script LOG] " + message);
    }

    public void debug(final String message) {
        System.out.println("[Script DEBUG] " + message);
    }

    public void warn(final String message) {
        System.out.println("[Script WARN] " + message);
    }

    public void error(final String message) {
        System.err.println("[Script ERROR] " + message);
    }

    public static void registerAsJavaMethod(V8 runtime) {
        V8Object v8Console = new V8Object(runtime);
        runtime.add("print", v8Console);
        v8Console.registerJavaMethod(console, "log", "log", new Class<?>[] { String.class });
        v8Console.registerJavaMethod(console, "debug", "debug", new Class<?>[] { String.class });
        v8Console.registerJavaMethod(console, "warn", "warn", new Class<?>[] { String.class });
        v8Console.registerJavaMethod(console, "error", "error", new Class<?>[] { String.class });
        v8Console.release();
    }
}
