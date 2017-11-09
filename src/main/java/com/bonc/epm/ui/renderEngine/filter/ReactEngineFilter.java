package com.bonc.epm.ui.renderEngine.filter;

import com.bonc.epm.ui.renderEngine.engines.ReactEngineProxy;
import com.bonc.epm.ui.renderEngine.context.AppContext;
import com.bonc.epm.ui.renderEngine.context.RenderingContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    private ReactEngineProxy react;

    public ReactEngineFilter() {
        react = new ReactEngineProxy("j2v8");
    }

    public ReactEngineFilter(String engineName) {
        react = new ReactEngineProxy(engineName);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String reactConfig = filterConfig.getInitParameter("reactConfig");
        if (reactConfig != null) {
            react = new ReactEngineProxy(reactConfig);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        ReactResponseWrapper crw = new ReactResponseWrapper(this, httpServletResponse);
        filterChain.doFilter(servletRequest, crw);

        String responseContentType = httpServletResponse.getContentType();

        if (responseContentType == null || responseContentType.contains("text/html")) {
            String requestExpectType = httpServletRequest.getHeader("Accept");

            String state = getRequestAttribute(httpServletRequest);

            OutputStream out = servletResponse.getOutputStream();

            if (requestExpectType.contains("json")) {
                servletResponse.setContentType("application/json");
                out.write(state.getBytes("UTF-8"));
            } else {
                AppContext context = new AppContext(httpServletRequest.getContextPath());
                RenderingContext routerCtx = new RenderingContext(context, httpServletRequest.getContextPath(), httpServletRequest.getRequestURI());

                String html = "EPM UI Java Integration: wait until server side render finished.";

                try {
                    html = react.render(state, routerCtx);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                servletResponse.setContentType("text/html;charset=UTF-8");
                out.write(html.getBytes("UTF-8"));
            }

            out.close();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}

    /**
     * separate model data from request
     * @param request
     * @return Map<String, Object>
     */
    private String getRequestAttribute(HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        Enumeration enumeration = request.getAttributeNames();

        if (enumeration.hasMoreElements()) {
            while (enumeration.hasMoreElements()) {
                String k = (String) enumeration.nextElement();
                String v = request.getAttribute(k).toString();
                map.put(k, v);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

}
