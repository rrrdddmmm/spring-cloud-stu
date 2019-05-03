package com.provider.service7003.controller;

import com.provider.service7003.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @GetMapping("/user")
    public User findUsers(){
        System.out.println("===============================/user===================================");
        return new User(11L, "rbb",25);
    }

    @GetMapping("/host")
    public Object findHost(){
        System.out.println("===============================/host===================================");
        List<String> strvices = discoveryClient.getServices();
        System.out.println("================================" + strvices);
        List<ServiceInstance> instances = this.discoveryClient.getInstances(userServiceUrl);
        for (ServiceInstance serviceInstance: instances) {
            System.out.println("微服务的ID" + serviceInstance.getServiceId());
            System.out.println("主机名称" + serviceInstance.getHost());
            System.out.println("端口" + serviceInstance.getPort());
            System.out.println("地址"+ serviceInstance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        System.out.println("===============================/user-instance===================================");
        return this.discoveryClient.getInstances(userServiceUrl);
    }


    /**
     * 插入数据
     * @param user
     * @return
     */
    @PostMapping("/user-insert")
    User post(@RequestBody User user) {
        return user;
    }

    /**
     * Get方式一获取数据
     * @param id
     * @param username
     * @param age
     * @return
     */
    @GetMapping("/user-get-one")
    User get1(@RequestParam("id") Long id,
              @RequestParam("username") String username,
              @RequestParam("age") Integer age) {
//      return new User().setAge(age).setId(id).setUsername(username);
        User use = new User();
        use.setAge(age);
        use.setId(id);
        use.setUsername(username);
        return use;
    }

    /**
     * Get方式二获取数据
     * @param map
     * @return
     */
    @GetMapping("/user-get-two")
    User get2(@RequestParam Map<String, Object> map) {
        User use = new User();
        use.setAge(Integer.parseInt(map.get("age").toString()));
        use.setId(Long.parseLong(map.get("id").toString()));
        use.setUsername(map.get("username").toString());
        return use;
    }
}
