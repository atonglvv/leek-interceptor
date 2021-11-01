package cn.atong.leek.leekinterceptor.modifyRequest;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

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
        log.info("==RequestWrapperFilter启动==");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        ItemCHttpServletRequestWrapper requestWrapper = new ItemCHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
    }
}
