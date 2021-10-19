package cn.atong.leek.leekinterceptor.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: leek-interceptor
 * @description:
 * @author: atong
 * @create: 2021-10-19 23:02
 */
@Component
@Aspect
public class GetMidAspect {

    @Pointcut("execution(public * cn.atong.leek.leekinterceptor.controller.*.*(..))")
    public void getMidPointCut(){}

    @Around("getMidPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        // 获取请求参数数组
        Object[] args = joinPoint.getArgs();
        Object obj = args[0];
        Class<?> aClass = obj.getClass();

        // 将实体转换json对象
        JSONObject json = (JSONObject) JSON.toJSON(obj);
        System.out.println("GetMidAspect->args(old) = " + json);
        // 修改json对象
        json.put("mid", 1);
        System.out.println("GetMidAspect->args(new) = " + json);
        // 把修改后的json对象转换为接收到参数的对象保存到数组中
        args[0] = JSON.toJavaObject(json, aClass);
        // 把数组封装到joinPoint对象返回
        return joinPoint.proceed(args);
    }
}
