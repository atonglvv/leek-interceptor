package cn.atong.leek.leekinterceptor.pojo;

import lombok.Data;

/**
 * @program: leek-interceptor
 * @description: 用户实体类
 * @author: atong
 * @create: 2021-10-19 20:14
 */
@Data
public class User {
    private Long id;
    private Long cid;
    private Long mid;
    private String name;
}
