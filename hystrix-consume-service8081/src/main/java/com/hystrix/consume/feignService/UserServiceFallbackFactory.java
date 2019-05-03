package com.hystrix.consume.feignService;

import com.hystrix.consume.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component  //不要忘记添加
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService(){

            @Override
            public User user(Long id) {
                return new User(-1L, "客户端提示：error:当前id："+ id +"@HystrixCommand(fallbackMethod = \"userFallback\")",-1);
            }

            @Override
            public String host() {
                return null;
            }

            @Override
            public String logUserInstance() {
                return null;
            }

            @Override
            public User post(User user) {
                return null;
            }

            @Override
            public User get1(Long id, String username, Integer age) {
                return null;
            }

            @Override
            public User get2(Map<String, Object> map) {
                return null;
            }
        };
    }
}
