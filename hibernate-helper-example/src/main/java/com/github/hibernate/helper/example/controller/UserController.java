/*
 * UserController  1.0  2018-11-19
 */
package com.github.hibernate.helper.example.controller;

import com.github.hibernate.helper.example.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-19 zhuyan 初版
 */
@Slf4j
@RestController
@Api(tags = {"ExampleUser"}, description = "用户服务接口")
public class UserController {

    @Autowired
    private UserService userService;
}
