package com.cn.bonc.renderEngine.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.Map;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc CharResponseWrapper
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {
    private StringWriter writer;

    private ByteArrayOutputStream baos;

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public String toString() {
        if (writer != null) {
            return writer.toString();
        } else if (baos != null) {
            try {
                return baos.toString("UTF-8");
            } catch (UnsupportedEncodingException e) {
                // May not happen
                return null;
            }
        }
        throw new IllegalStateException("No response");
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> toJson() throws IOException {
        return new ObjectMapper().readValue(this.toString(), Map.class);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new StringWriter();
        }
        return new PrintWriter(writer);
    }

    /**
     * @see javax.servlet.ServletResponseWrapper#getOutputStream()
     */
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

            /**
             * @see java.io.OutputStream#write(byte[])
             */
            @Override
            public void write(byte[] b) throws IOException {
                baos.write(b);
            }

            /**
             * @see java.io.OutputStream#write(byte[], int, int)
             */
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
