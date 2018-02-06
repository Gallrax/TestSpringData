package com.cx.repository.redis;

import com.cx.entity.RedisUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/6
 * @Version: 1.0
 */
public interface RedisUserRepository extends CrudRepository<RedisUser, Integer> {

    List<RedisUser> findByName(String name);

    List<RedisUser> findBySurname(String surname);
}