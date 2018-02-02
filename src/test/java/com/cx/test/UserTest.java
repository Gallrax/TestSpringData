package com.cx.test;

import com.cx.entity.Address;
import com.cx.entity.User;
import com.cx.repository.jpa.AddressRepository;
import com.cx.repository.jpa.UserRepository;
import com.cx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/1
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//@ActiveProfiles("test")
//@WebAppConfiguration
public class UserTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;

    @Test
    public void test01() {
        User tom = userRepository.save(new User("Tom", "t", 1, "Tom@cx.com"));
        System.out.println(tom);
    }

    @Test
    public void test02() {
        User user = userRepository.findOne(2);
        System.out.println(user);
        if (null != user) {
//            System.out.println(user.getAddresses());
        }
    }

    @Test
    public void test03() {
        Address address = addressRepository.findOne(1);
        System.out.println(address);
    }

    @Test
    public void test04() {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("name"), "%a%");
            }
        };
        Page<User> userPage = userRepository.findAll(specification, new PageRequest(0, 10));
        List<User> users = userPage.getContent();
        System.out.println(users);
    }

    @Test
    public void test05() {
        Specification<Address> specification = new Specification<Address>() {
            @Override
            public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                //多表连接，通过user的sex进行查询
                Join<User, Address> join = root.join("user", JoinType.LEFT);
                list.add(criteriaBuilder.equal(join.<String>get("sex"), "1"));
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Page<Address> addressPage = addressRepository.findAll(specification, new PageRequest(0, 10));
        List<Address> addresses = addressPage.getContent();
        System.out.println(addresses);
    }

    @Test
    public void test06() {
        User user = new User();
        user.setId(2);
        List<Address> addresses = addressRepository.findByUser(user);
        System.out.println(addresses);
    }

    @Test
    public void test07() {
        List<Address> addresses = addressRepository.findByZipCodeLike("%0%");
        for (Address address : addresses) {
            System.out.println(address);
        }
    }

    @Test
    public void test08() {
        List<User> users = userRepository.findByNameOrSurname("a", "a");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test20() {
        User user = userService.save(new User("Tom", "t", 1, "Tom@cx.com"));
        System.out.println(user);
    }

}
