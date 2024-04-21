package com.nnutc.system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author admin
 */
@Configuration//配置类
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 BCryptPasswordEncoder 进行密码加密
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 创建基于内存的用户信息管理器
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        // 创建密码编码器
        PasswordEncoder passwordEncoder = passwordEncoder();

        // 使用密码编码器创建加密后的密码
        String encodedPassword = passwordEncoder.encode("123456");

        // 创建UserDetails对象，用于管理用户名、用户密码、用户角色、用户权限等内容
        UserDetails userDetails = User.withUsername("admin")
                .password(encodedPassword)
                .roles("USER")
                .build();

        // 将用户添加到用户信息管理器中
        inMemoryUserDetailsManager.createUser(userDetails);

        return inMemoryUserDetailsManager;
    }
}