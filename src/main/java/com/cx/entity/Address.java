package com.cx.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/2
 * @Version: 1.0
 */
@Entity(name = "cx_address")
@Data
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "zipcode")//默认会对zipCode -> zip_code
    private Integer zipCode;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", insertable = false, updatable = false)
    private User user;
}
