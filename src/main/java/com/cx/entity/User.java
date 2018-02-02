package com.cx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/1
 * @Version: 1.0
 */
@Entity(name = "cx_user")
@Data//@Data 包含了 @ToString、@EqualsAndHashCode、@Getter/@Setter和@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    private Integer sex;
    private String email;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "uid")
//    private List<Address> addresses;

    public User(String name, String surname, Integer sex, String email) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.email = email;
    }
}
