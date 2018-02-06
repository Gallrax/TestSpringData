package com.cx.repository.es;

import com.cx.entity.ESUser;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/6
 * @Version: 1.0
 */
public interface ESUserRepository extends ElasticsearchRepository<ESUser, Integer> {

    List<ESUser> findByName(String name);

    List<ESUser> findBySurname(String surname);

    @Query(value = "{\"bool\":{\"must\":{\"term\":{\"name\":\"?0\"}}}}")
    List<ESUser> tempByName();
}
