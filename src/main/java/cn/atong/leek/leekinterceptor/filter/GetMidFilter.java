package cn.atong.leek.leekinterceptor.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sun.misc.BASE64Decoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: leek-interceptor
 * @description:
 * @author: atong
 * @create: 2021-10-19 22:55
 */
//@Component
@Slf4j
public class GetMidFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        // 获取你需要的参数
        String param = request.getParameter("key");
        if (StringUtils.isBlank(param)) {
            chain.doFilter(request, response);
        } else {
            byte[] bytes = new BASE64Decoder().decodeBuffer(param);
            String decode = new String(bytes);
            Map<String, Object> map = new HashMap<>();
            map.put("key", new String[]{decode});
            RequestParameterWrapper wrapper = new RequestParameterWrapper(request);
            wrapper.addParameters(map);
            chain.doFilter(wrapper, response);
        }

    }

}
