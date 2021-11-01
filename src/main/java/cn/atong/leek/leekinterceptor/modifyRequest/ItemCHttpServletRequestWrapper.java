package cn.atong.leek.leekinterceptor.modifyRequest;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ItemCHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * 缓存下来的HTTP body
     */
    private String body;

    /**
     * @description  解析request的inputStream(即body)数据，转成字符串
     * @param request: HttpServletRequest
     * @author atong
     * @date 2021/11/1 23:30
     */
    public ItemCHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        body = stringBuilder.toString();
    }

    /**
     * 重新包装输入流
     *
     * @return ServletInputStream
     */
    @Override
    public ServletInputStream getInputStream() {

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
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

//            @Override
//            public int readLine(byte[] b, int off, int len) throws IOException {
//                return super.readLine(b, off, len);
//            }
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
