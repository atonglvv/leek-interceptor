package cn.atong.leek.leekinterceptor.interceptor;

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
public class ItemHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * 缓存下来的HTTP body
     */
    private byte[] body;

    public ItemHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    /**
     * 重新包装输入流
     *
     * @return ServletInputStream
     * @throws IOException IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {

        InputStream bodyStream = new ByteArrayInputStream(body);

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
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        InputStream bodyStream = new ByteArrayInputStream(body);
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
