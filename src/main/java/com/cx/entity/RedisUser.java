package com.cx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/6
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RedisHash
public class RedisUser {

    @Id
    private Integer id;
//    @Indexed
    private String name;
//    @Indexed
    private String surname;
    private Integer sex;
    private String email;

    public RedisUser(String name, String surname, Integer sex, String email) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.email = email;
    }
}
