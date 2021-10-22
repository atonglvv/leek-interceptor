package cn.atong.leek.leekinterceptor.controller;

import cn.atong.leek.leekinterceptor.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * @program: leek-interceptor
 * @description: Hello Controller
 * @author: atong
 * @create: 2021-10-19 18:56
 */
@RestController
public class HelloController {

    @RequestMapping(value = "hello", produces = { "application/json" })
    public String hello(@RequestBody User user) {
        return "Hello Controller.User = " + user.toString();
    }
}
