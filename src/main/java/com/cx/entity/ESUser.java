package com.cx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/6
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "testspringdata", type = "esUser")
public class ESUser {

    @Id
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.Integer)
    private Integer id;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String name;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String surname;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.Integer)
    private Integer sex;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String email;

    public ESUser(String name, String surname, Integer sex, String email) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.email = email;
    }
}
