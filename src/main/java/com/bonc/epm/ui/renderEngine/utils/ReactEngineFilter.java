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
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String responseContentType = httpServletResponse.getContentType();
        String requestAccept = httpServletRequest.getHeader("Accept");

        if (responseContentType == null && requestAccept == null ) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (!requestAccept.contains("text/html") && responseContentType == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (requestAccept.contains("application/json") && responseContentType == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }  else if (responseContentType != null && responseContentType.contains("application/json")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            CharResponseWrapper crw = new CharResponseWrapper(httpServletResponse);
            filterChain.doFilter(servletRequest, crw);

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
        }
    }

    @Override
    public void destroy() {}

    /**
     * separate model data from request
     * @param request
     * @return Map<String, Object>
     */
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
