package com.cx.repository.jpa;

import com.cx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/1
 * @Version: 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * @Author: 冯冠凯 
     * @Description: 根据名称返回(eq) ps:spring data会根据方法名进行实现
     * @Date: Created on 2018/2/1
     * @Version: 1.0
     */
    List<User> findBySex(Integer sex);

    List<User> findByNameOrSurname(String name, String surname);
}
