package com.nnutc.system.config;


import com.nnutc.system.custom.CustomMd5Password;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration//配置类
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //拦截设置
        http.authorizeRequests(
                        authorize -> authorize
//                                对所有请求开启授权保护
                                .anyRequest()
//                                已认证的请求会被自动授权
                                .authenticated()
                )
                .formLogin(withDefaults());//使用表单授权方式
        return http.build();
    }


    //    配置密码加密器
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new CustomMd5Password();
    }


    @Bean
    public UserDetailsService userDetailsService() {
//    创建基于数据库的用户信息管理器
        DBUserDetailsManager manager = new DBUserDetailsManager();
        return manager;
    }
}
