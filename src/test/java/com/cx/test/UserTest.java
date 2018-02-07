package com.cx.test;

import com.cx.entity.Address;
import com.cx.entity.ESUser;
import com.cx.entity.RedisUser;
import com.cx.entity.User;
import com.cx.repository.es.ESUserRepository;
import com.cx.repository.jpa.AddressRepository;
import com.cx.repository.jpa.UserRepository;
import com.cx.repository.redis.RedisUserRepository;
import com.cx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
        @Autowired
    private RedisUserRepository redisUserRepository;
    @Autowired
    private ESUserRepository esUserRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private JedisConnectionFactory factory;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
    public void test09() {
        List<Address> addresses = addressRepository.findByUserSex(1);
        for (Address address : addresses) {
            System.out.println(address);
        }
    }

    @Test
    public void test20() {
        User user = userService.save(new User("Tom", "t", 1, "Tom@cx.com"));
        System.out.println(user);
    }

    @Test
    public void test21() {
        RedisUser redisUser1 = new RedisUser(1, "Tom", "t", 1, "Tom@cx.com");
        RedisUser redisUser2 = new RedisUser(2, "Aa", "a", 1, "Aa@cx.com");
        RedisUser redisUser3 = new RedisUser(3, "Bb", "b", 1, "Bb@cx.com");
//        RedisUser tempUser1 = redisUserRepository.save(redisUser1);
//        RedisUser tempUser2 = redisUserRepository.save(redisUser2);
//        RedisUser tempUser3 = redisUserRepository.save(redisUser3);
    }

    @Test
    public void test22() {
        ESUser esUser1 = new ESUser(1, "Tom", "t", 1, "Tom@cx.com");
        ESUser esUser2 = new ESUser(2, "Aa", "a", 1, "Aa@cx.com");
        ESUser esUser3 = new ESUser(3, "Bb", "b", 1, "Bb@cx.com");
        esUserRepository.save(esUser1);
        esUserRepository.save(esUser2);
        esUserRepository.save(esUser3);
    }

    @Test
    public void test24() {
        Iterable<ESUser> esUsers = esUserRepository.findAll();
        for (ESUser esUser : esUsers) {
            System.out.println(esUser);
        }
    }

    @Test
    public void test25() {
        List<ESUser> esUsers = esUserRepository.findByName("Aa");
        for (ESUser esUser : esUsers) {
            System.out.println(esUser);
        }

    }

    @Test
    public void test26() {
        ESUser esUser = new ESUser("Aa", "a", 1, "Aa@cx.com");
        esUserRepository.index(esUser);
    }

    @Test
    public void test27() {
        RedisUser redisUser = new RedisUser("Cc", "c", 1, "Cc@cx.com");
        RedisUser user = redisUserRepository.save(redisUser);
        System.out.println(user);
    }

    @Test
    public void test28() {
        Iterable<RedisUser> redisUsers = redisUserRepository.findAll();
        for (RedisUser redisUser : redisUsers) {
            System.out.println(redisUser);
        }
    }

}
