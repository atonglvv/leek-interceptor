package cn.atong.leek.leekinterceptor.modifyRequest;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @program: leek-interceptor
 * @description: 定义一个过滤器，以过滤相应请求，让它们使用包装过的HttpServletRequest
 * @author: atong
 * @create: 2021-11-01 22:25
 */
@Slf4j
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
        log.info("======RequestWrapperFilter启动======");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("======RequestWrapperFilter.doFilter======");
        ItemCHttpServletRequestWrapper requestWrapper = null;
        try {
            HttpServletRequest req = (HttpServletRequest)request;
            requestWrapper = new ItemCHttpServletRequestWrapper(req);
        } catch (Exception e) {
            log.warn("ItemCHttpServletRequestWrapper Error:", e);
        }
        chain.doFilter(requestWrapper, response);
        chain.doFilter((Objects.isNull(requestWrapper) ? request : requestWrapper), response);
    }

    @Override
    public void destroy() {
    }
}
