package com.feign.service.feignService;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * 多个接口上的@FeignClient(“相同服务名”)会报错，overriding is disabled，即出现了相同的Bean名。
 */

//@FeignClient(name="DICTSERVICE-PROVIDER-USER", configuration = FooConfiguration.class)
//@FeignClient("DICTSERVICE-PROVIDER-USER")
public interface BookService {
    /**
     * 使用feign自带的注解
     * @return
     */
//    @RequestLine("GET /user")
    @GetMapping(value = "/user")
    String user() ;

    @GetMapping(value = "/host")
    String host() ;

    @GetMapping(value = "/user-instance")
    String logUserInstance() ;
}
