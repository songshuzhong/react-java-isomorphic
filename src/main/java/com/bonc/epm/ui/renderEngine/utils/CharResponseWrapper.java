package com.bonc.epm.ui.renderEngine.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc CharResponseWrapper
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    private PrintWriter writer = new PrintWriter(baos);

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (baos == null) {
            baos = new ByteArrayOutputStream();
        }
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

            @Override
            public void setWriteListener(WriteListener listener) {
            }

            @Override
            public boolean isReady() {
                return true;
            }
        };
    }

}
