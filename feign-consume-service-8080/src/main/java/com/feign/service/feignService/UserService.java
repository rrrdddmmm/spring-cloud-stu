package com.feign.service.feignService;

import com.feign.confg.FeignConfiguration;
import com.feign.service.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="DICTSERVICE-PROVIDER-USER", configuration = FeignConfiguration.class)
//@FeignClient("DICTSERVICE-PROVIDER-USER")
//@FeignClient(name="FeignClient名称", url="服务提供者url", configuration = FeignConfiguration.class)
public interface UserService {

    /**
     * 使用feign自带的注解
     * @return
     */

    @GetMapping(value = "/user/{id}")  // springmvc注解
//    @RequestLine("GET /user")  // feign自带注解
    String user(@PathVariable("id") Long id) ;

    @GetMapping(value = "/host")
//    @RequestLine("GET /user")
    String host() ;

    @GetMapping(value = "/user-instance")
//    @RequestLine("GET /user-instance")
    String logUserInstance() ;

    /**
     * 插入数据
     * @param user
     * @return
     */

//    @RequestLine("POST /user-insert")
    @PostMapping(value = "/user-insert")
    User post(User user) ;

    /**
     * Get方式一获取数据
     * @return
     */
//    @RequestLine("GET /user-get-one")
    @GetMapping(value = "/user-get-one")
    User get1(@RequestParam("id") Long id,
              @RequestParam("username") String username,
              @RequestParam("age") Integer age) ;

    /**
     * Get方式二获取数据
     * @param map
     * @return
     */
//    @RequestLine("GET /user-get-two")
    @GetMapping(value = "/user-get-two")
    User get2(@RequestParam Map<String, Object> map) ;

//    /**
//     * Get方式二获取数据
//     * @param map
//     * @return
//     */
//    @GetMapping(value = "/user-get-three")
//    User get3(User user) ;
}
