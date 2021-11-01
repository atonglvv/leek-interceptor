package cn.atong.leek.leekinterceptor.modifyRequest;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @program: leek-interceptor
 * @description:
 * @author: atong
 * @create: 2021-10-19 22:36
 */
public class ItemCHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * 缓存下来的HTTP body
     */
    private String body;

    public ItemCHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
        body = new String(bodyBytes, request.getCharacterEncoding());
    }

    /**
     * 重新包装输入流
     *
     * @return ServletInputStream
     * @throws IOException IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {

        InputStream bodyStream = new ByteArrayInputStream(body.getBytes());

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bodyStream.read();
            }

            /**
             * 下面的方法一般情况下不会被使用，如果你引入了一些需要使用ServletInputStream的外部组件，可以重点关注一下。
             * @return boolean
             */
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int readLine(byte[] b, int off, int len) throws IOException {
                return super.readLine(b, off, len);
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
