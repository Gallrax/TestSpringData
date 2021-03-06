package com.cx.repository.jpa;

import com.cx.entity.Address;
import com.cx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/2
 * @Version: 1.0
 */
public interface AddressRepository extends JpaRepository<Address, Integer>, JpaSpecificationExecutor<Address> {

    List<Address> findByUser(User user);

    List<Address> findByZipCodeLike(String zipCode);

    /**
     * @Author: 冯冠凯 
     * @Description: 根据Address的user属性的sex属性进行查询 -> ByUserSex
     * @Date: Created on 2018/2/2
     * @Version: 1.0
     */
    List<Address> findByUserSex(Integer sex);
}
