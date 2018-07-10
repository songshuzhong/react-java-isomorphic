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
    private CharArrayWriter bufferedWriter;
    private PrintWriter writer;

    public ReactResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        bufferedWriter = new CharArrayWriter();
        writer = new PrintWriter(bufferedWriter);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    public byte[] getContent() {
        byte[] bytes = bufferedWriter.toString().getBytes();
        return bytes;
    }

    @Override
    public void sendError(int sc) throws IOException {
        if (sc == SC_NOT_FOUND) {
            super.setStatus(SC_OK);
        } else {
            super.setStatus(sc);
        }
    }
}
