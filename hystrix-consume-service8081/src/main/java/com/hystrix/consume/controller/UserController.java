package com.hystrix.consume.controller;


import com.hystrix.consume.entity.User;
import com.hystrix.consume.feignService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/user/{id}")
    public User user(@PathVariable("id") Long id) {
        System.out.println("===============================/user===================================");
        return userService.user(id);
    }

    @GetMapping(value = "/host")
    public String host() {
        System.out.println("===============================/host===================================");
        return userService.host();
    }

    @GetMapping(value = "/instance")
    public String logUserInstance() {
        System.out.println("===============================/instance===================================");
        return userService.logUserInstance();
    }


    /**
     * 插入数据
     * @param
     * @return
     */
    @PostMapping("/user-insert")
    public String post(@RequestParam String username) {
        User user1 = userService.post(new User());
        System.out.println("=-=============================================================");
        return username;
    }

    /**
     * Get方式一获取数据
     * @return
     */
    @GetMapping("/user-get-one")
    User get1(@RequestParam("id") Long id,
              @RequestParam("username") String username,
              @RequestParam("age") Integer age) {
        return userService.get1(id, username, age);
    }

    /**
     * Get方式二获取数据
     * @param map
     * @return
     */
    @GetMapping("/user-get-two")
    User get2(@RequestParam Map<String, Object> map) {
        return userService.get2(map);
    }
}
