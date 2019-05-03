//package com.register.confg;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * spring security配置
// */
//@EnableWebSecurity
//@Configuration
//public class SercurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable(); //关闭csrf
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //开启认证
//    }
//}
