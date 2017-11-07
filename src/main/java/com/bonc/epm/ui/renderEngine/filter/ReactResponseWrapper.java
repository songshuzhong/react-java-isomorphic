package com.bonc.epm.ui.renderEngine.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc ReactResponseWrapper
 */
public class ReactResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintWriter writer = new PrintWriter(baos);
    private int status = SC_OK;

    public ReactResponseWrapper(ReactEngineFilter filter, HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public void sendError(int sc) throws IOException {
        this.status = sc;
        if (isFound()) {
            super.sendError(SC_OK);
        } else {
            super.setStatus(SC_OK);
        }
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        this.status = sc;
        if (isFound()) {
            super.sendError(SC_OK, msg);
        } else {
            super.setStatus(SC_OK);
        }
    }

    public void setStatus(int status) {
        this.status = status;
        super.setStatus(status);
    }

    @Override
    public void reset() {
        this.status = SC_OK;
        super.reset();
    }

    @Override
    public void addHeader(String name, String value) {
        super.addHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {

            @Override
            public void write(int b) throws IOException {
                baos.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                baos.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                baos.write(b, off, len);
            }

        };
    }

    public boolean isFound() {
        return status != SC_NOT_FOUND;
    }

}
