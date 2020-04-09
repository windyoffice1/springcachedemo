package com.windyoffice.springcachedemo.interceptor;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

public class CustomerRequestWrapper extends HttpServletRequestWrapper {

    private final  byte[] body;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CustomerRequestWrapper(HttpServletRequest request) {
        super(request);
        String string=getBodyFromRequest(request);
        body=string.getBytes(Charset.forName("utf-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream(){
        final ByteArrayInputStream bais=new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    private String getBodyFromRequest(HttpServletRequest request) {
        StringBuilder stringBuilder=new StringBuilder();
        InputStream in =null;
        BufferedReader reader=null;
        try {
            in=request.getInputStream();
            reader=new BufferedReader(new InputStreamReader(in,Charset.forName("utf-8")));
            String tempLine=null;
            while ((tempLine=reader.readLine())!=null){
                stringBuilder.append(tempLine);
            }
            return stringBuilder.toString();
        }catch (IOException e){

        }
            return stringBuilder.toString();
    }
}
