package cn.atong.leek.leekinterceptor.modifyRequest;

import cn.atong.leek.leekinterceptor.pojo.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: leek-interceptor
 * @description: 通过拦截器对body中的值进行修改
 * @author: atong
 * @create: 2021-11-01 22:27
 */
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        System.out.println("------WebInterceptor拦截器preHandle------");
        /*
         * 获取POST请求body
         *  ServletInputStream(CoyoteInputStream) 输入流无法重复调用
         */
//        byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
//        String body = new String(bodyBytes, request.getCharacterEncoding());
//        System.out.println("body = " + body);

        ItemCHttpServletRequestWrapper requestWrapper = new ItemCHttpServletRequestWrapper(request);
        String body = requestWrapper.getBody();
        System.out.println("webInterceptor body = " + body);
        JSONObject requestObject = JSON.parseObject(body);
        Object json = requestObject.get("json");
        if (null == json) {
            log.info("WebInterceptor, request不含json");
            return false;
        }else {
            String target = requestObject.getJSONObject("json").getJSONObject("header").getString("target");
            log.info("WebInterceptor, request.json.header.target = " + target);
            String accessToken = requestObject.getJSONObject("json").getJSONObject("header").getString("accessToken");
            log.info("WebInterceptor, request.json.header.accessToken = " + accessToken);
            User user = requestObject.getJSONObject("json").getObject("data", User.class);
            log.info("WebInterceptor, request.json.header.body = " + JSON.toJSONString(user));
            user.setMemberId(233L);
            requestWrapper.setBody(JSON.toJSONString(user));
        }
        return true;
    }
}
