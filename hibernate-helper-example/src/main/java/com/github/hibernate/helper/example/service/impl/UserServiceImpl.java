/*
 * UserServiceImpl  1.0  2018-11-20
 */
package com.github.hibernate.helper.example.service.impl;

import com.github.hibernate.helper.example.dao.UserDao;
import com.github.hibernate.helper.example.entity.User;
import com.github.hibernate.helper.example.service.UserService;
import com.github.hibernate.helper.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * [关于类内容的描述]
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-20 zhuyan 初版
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Autowired
    private UserRepository userRepository;

    public User findByUserCode(String userCode) {
        return userRepository.findByUserCode(userCode);
    }


}