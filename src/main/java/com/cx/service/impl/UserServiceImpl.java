package com.cx.service.impl;

import com.cx.entity.User;
import com.cx.repository.jpa.UserRepository;
import com.cx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/2
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @Author: 冯冠凯
     * @Description: 因为jpa实现类中已加transactional，为了保证service对事物的控制，必须要有@Transactional
     * @Date: Created on 2018/2/2
     * @Version: 1.0
     */
    @Override
    @Transactional
    public User save(User user) {
        User resultUser = userRepository.save(user);
        return resultUser;
    }
}
