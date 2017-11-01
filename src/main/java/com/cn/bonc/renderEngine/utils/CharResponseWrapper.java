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

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    private PrintWriter writer = new PrintWriter(baos);

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
    }

/*
    @Override
    public String toString() {
        if (writer != null) {
            String s = writer.toString();
            return s;
        } else if (baos != null) {
            try {
                String s = baos.toString("UTF-8");
                return s;
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
*/

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
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

    public ByteArrayOutputStream getByteArrayOutPutStream() {
        return baos;
    }

    public String getTextContent() {
        flush();
        return baos.toString();
    }

    public void flush() {
        try {
            writer.flush();
            writer.close();
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
