package com.cn.bonc.renderEngine.utils;

import com.cn.bonc.renderEngine.j2v8.J2V8ReactEnginer;
import com.cn.bonc.renderEngine.context.AppContext;
import com.cn.bonc.renderEngine.context.RenderingContext;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.coyote.OutputBuffer;
import org.apache.tomcat.util.buf.ByteChunk;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc ReactEngineFilter
 */
public class ReactEngineFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        CharResponseWrapper crw = new CharResponseWrapper(httpServletResponse);

        filterChain.doFilter(servletRequest, crw);

        String responseContentType = httpServletResponse.getContentType();
        String requestAccept = httpServletRequest.getHeader("Accept");
        System.out.println(requestAccept + " " + responseContentType + " " + ((HttpServletRequest) servletRequest).getRequestURI());

        if (responseContentType == null && requestAccept == null ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (requestAccept.contains("text/html") || responseContentType.contains("text/html")) {

            Map<String, Object> state = new HashMap<>();
            AppContext context = new AppContext(httpServletRequest.getContextPath());
            RenderingContext routerCtx = new RenderingContext(context, httpServletRequest.getContextPath(), httpServletRequest.getRequestURI());

            J2V8ReactEnginer react = new J2V8ReactEnginer();
            String html = "EPM UI Java Integration: wait until server side render finished.";

            try {
                html = react.render(state, routerCtx);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(html);
            servletResponse.setContentType("text/html;charset=UTF-8");

            PrintWriter out = servletResponse.getWriter();
            out.write(html);
            out.close();
        }
    }

    @Override
    public void destroy() {}

    private String getByteChunk(ServletResponse servletResponse) throws Exception {
        CoyoteOutputStream os = (CoyoteOutputStream)servletResponse.getOutputStream();
        Class<CoyoteOutputStream> c = CoyoteOutputStream.class;
        String byteChunks = "";
        Field fs = c.getDeclaredField("ob");
        if (fs.getType().toString().endsWith("OutputBuffer")) {
            fs.setAccessible(true);
            OutputBuffer ob = (OutputBuffer) fs.get(os);
            Class<OutputBuffer> cc = OutputBuffer.class;
            Field ff = cc.getDeclaredField("outputChunk");
            ff.setAccessible(true);
            if (ff.getType().toString().endsWith("ByteChunk")) {
                ByteChunk bc = (ByteChunk) ff.get(ob);
                byteChunks = new String(bc.getBytes(), "UTF-8");
            }
        }
        return byteChunks;
    }
}
