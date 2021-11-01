package cn.atong.leek.leekinterceptor.modifyRequest;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * @program: leek-interceptor
 * @description: 在WebMvcConfigurer中注册过滤器与拦截器
 * @author: atong
 * @create: 2021-11-01 22:26
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public WebInterceptor webInterceptor() {
        return new WebInterceptor();
    }

    @Bean
    public FilterRegistrationBean addRequestWrapperFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestWrapperFilter());
        registration.setName("RequestWrapperFilter");
        registration.addUrlPatterns("/**");
        registration.setOrder(1);
        return registration;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor()).addPathPatterns("/**");
    }
}
