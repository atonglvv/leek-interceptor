package cn.atong.leek.leekinterceptor.interceptor;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: leek-interceptor
 * @description:
 * @author: atong
 * @create: 2021-10-19 22:39
 */
public class ItemDispatcherServlet extends DispatcherServlet {

    /**
     * 包装成我们自定义的request
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception Exception
     */
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.doDispatch(new ItemHttpServletRequestWrapper(request), response);
    }
}
