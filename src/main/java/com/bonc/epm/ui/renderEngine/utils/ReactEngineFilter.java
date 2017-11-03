package com.bonc.epm.ui.renderEngine.utils;

import com.bonc.epm.ui.renderEngine.context.AppContext;
import com.bonc.epm.ui.renderEngine.context.RenderingContext;
import com.bonc.epm.ui.renderEngine.j2v8.J2V8ReactEnginer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        ReactResponseWrapper crw = new ReactResponseWrapper(httpServletResponse);
        filterChain.doFilter(servletRequest, crw);

        String responseContentType = httpServletResponse.getContentType();

        if (responseContentType == null || responseContentType.contains("text/html")) {
            Map<String, Object> state = getRequestData(httpServletRequest);
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
            out.write(html);
            out.close();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}

    private Map<String,Object> getRequestData(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Enumeration enumeration = request.getAttributeNames();

        if (enumeration.hasMoreElements()) {
            while (enumeration.hasMoreElements()) {
                String inputName = (String) enumeration.nextElement();
                map.put(inputName, request.getParameter(inputName));
            }
        }

        return map;
    }

}
