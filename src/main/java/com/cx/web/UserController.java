package com.cx.web;

import com.cx.entity.User;
import com.cx.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/1
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String index() {
        userRepository.save(new User("Aa", "a", 1, "Aa@cx.com"));
        userRepository.save(new User("Bb", "b", 2, "Bb@cx.com"));
        userRepository.save(new User("Cc", "c", 1, "Cc@cx.com"));
        userRepository.save(new User("Dd", "d", 3, "Dd@cx.com"));
        userRepository.save(new User("Ee", "ae", 1, "Ee@cx.com"));
        return "ok";
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping("/findById/{id}")
    public User findById(@PathVariable Integer id) {
        User user = userRepository.findOne(id);
        return user;
    }

    @RequestMapping("/findBySex/{sex}")
    public List<User> findBySex(@PathVariable Integer sex) {
        List<User> users = userRepository.findBySex(sex);
        return users;
    }

    @RequestMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id) {
        userRepository.delete(id);
        return 1;
    }
}
