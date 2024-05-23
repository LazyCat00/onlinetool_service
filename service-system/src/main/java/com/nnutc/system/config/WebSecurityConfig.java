package com.nnutc.system.config;


import com.nnutc.system.custom.CustomMd5Password;
import com.nnutc.system.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Objects;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration//配置类
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
@Autowired
private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 拦截设置
        http.authorizeRequests(
                        authorize -> authorize
                                // 允许登录/注册接口访问
                                .requestMatchers(HttpMethod.POST, "/index/login").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                                // 允许swagger访问
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/doc.html/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/webjars/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/feedback/**").permitAll() // 允许所有用户访问 /feedback/query 接口
                                // 对所有请求开启授权保护
                                .anyRequest()
                                // 已认证的请求会被自动授权
                                .authenticated()


                );
//                .formLogin(form -> {
//                    form
//                            .loginPage("http://localhost:5173/#/login")
//                            .usernameParameter("username")//配置自定义的表单用户名参数
//                            .passwordParameter("password")//配置自定义的表单密码名参数
//                            .successHandler(new MyAuthenticationSuccessHandler())//认证成功时的处理
//                    ;
//                });//使用表单授权方式

http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        开启跨域
        http.cors(withDefaults());
        // 关闭cscrf攻击防御
        http
                .csrf(cscrf -> {
                    cscrf.disable();
                });

        return http.build();

    }


    //    配置密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(4);
        return new CustomMd5Password();
    }


    @Bean
    public UserDetailsService userDetailsService() {
//    创建基于数据库的用户信息管理器
        DBUserDetailsManager manager = new DBUserDetailsManager();
        return manager;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                从Authentication对象中获取用户名和身份凭证信息
                String username = authentication.getName();
                String password = authentication.getCredentials().toString();

                UserDetails loginUser = userDetailsService().loadUserByUsername(username);
                if (Objects.isNull(loginUser)|| !passwordEncoder().matches(password,loginUser.getPassword())){
//                    密码匹配失败抛出异常
                    throw new BadCredentialsException("访问拒绝，用户名或密码错误！");
                }
                return new UsernamePasswordAuthenticationToken(loginUser,password,loginUser.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        };
    }


}
