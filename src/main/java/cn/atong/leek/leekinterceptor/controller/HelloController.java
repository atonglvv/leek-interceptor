package cn.atong.leek.leekinterceptor.controller;

import cn.atong.leek.leekinterceptor.pojo.User;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: leek-interceptor
 * @description: Hello Controller
 * @author: atong
 * @create: 2021-10-19 18:56
 */
@Slf4j
@RestController
public class HelloController {

    @RequestMapping(value = "hello", produces = {"application/json"})
    public String hello(@RequestBody User user) {
        log.info("HelloController.user = {}", JSON.toJSONString(user));
        return "Hello Controller.User = " + user.toString();
    }
}
