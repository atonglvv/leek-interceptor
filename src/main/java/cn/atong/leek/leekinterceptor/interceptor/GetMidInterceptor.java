package cn.atong.leek.leekinterceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * @program: leek-interceptor
 * @description: 获取mid Interceptor
 * @author: atong
 * @create: 2021-10-19 20:34
 */
@Slf4j
public class GetMidInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        System.out.println("------拦截器preHandle------");
        /*
        String changeSessionId = request.changeSessionId();
        System.out.println("request.changeSessionId = " + changeSessionId);
        String authType = request.getAuthType();
        System.out.println("request.getAuthType = " + authType);
        String contextPath = request.getContextPath();
        System.out.println("request.getContextPath = " + contextPath);
        Cookie[] cookies = request.getCookies();
        System.out.println("request.getCookies = " + cookies.toString());
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println("request.getHeaderNames = " + headerNames);
        HttpServletMapping httpServletMapping = request.getHttpServletMapping();
        System.out.println("request.getHttpServletMapping = " + httpServletMapping);
        String method = request.getMethod();
        System.out.println("request.getMethod = " + method);
        Collection<Part> parts = request.getParts();
        System.out.println("request.getParts = " + parts);
         */

//        String requestURI = request.getRequestURI();
//        System.out.println("request.getRequestURI = " + requestURI);
//        StringBuffer requestURL = request.getRequestURL();
//        System.out.println("request.getRequestURL = " + requestURL);
//        String contextPath = request.getContextPath();
//        System.out.println("request.getContextPath = " + contextPath);
//
//        Enumeration<String> parameterNames = request.getParameterNames();
//        System.out.println("request.getParameterNames  = " + parameterNames);
//        Map<String, String> params = new HashMap<>();
//        while (parameterNames.hasMoreElements()) {
//            String parameter = parameterNames.nextElement();
//            String value = request.getParameter(parameter);
//            params.put(parameter, value);
//            System.out.println("parameter = " + parameter);
//            System.out.println("value = " + value);
//        }
//
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        System.out.println("request.getParameterMap = " + parameterMap);
//
//        //获取GET请求参数
//        String queryString = request.getQueryString();
//        System.out.println("request.getQueryString = " + queryString);


        /*
         * 获取POST请求body
         *  ServletInputStream(CoyoteInputStream) 输入流无法重复调用
         */
        byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
        String body = new String(bodyBytes, request.getCharacterEncoding());
        System.out.println("body = " + body);



        return true;
    }
}
