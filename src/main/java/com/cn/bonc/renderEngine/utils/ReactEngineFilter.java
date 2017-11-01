package com.cn.bonc.renderEngine.utils;

import com.cn.bonc.renderEngine.j2v8.J2V8ReactEnginer;
import com.cn.bonc.renderEngine.context.AppContext;
import com.cn.bonc.renderEngine.context.RenderingContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc ReactEngineFilter
 */
public class ReactEngineFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        filterChain.doFilter(servletRequest, servletResponse);

        String responseContentType = httpServletResponse.getContentType();
        String requestAccept = httpServletRequest.getHeader("Accept");

        if (responseContentType == null && requestAccept == null ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (responseContentType == null && requestAccept.contains("text/html")) {
            CharResponseWrapper crw = new CharResponseWrapper(httpServletResponse);

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

            servletResponse.setContentType("text/html;charset=UTF-8");
            PrintWriter out = servletResponse.getWriter();
            out.print(html);
            out.close();
        } else {
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
