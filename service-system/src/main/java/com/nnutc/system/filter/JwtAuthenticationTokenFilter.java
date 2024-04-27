package com.nnutc.system.filter;

import com.nnutc.common.utils.JwtHelper;
import com.nnutc.model.entity.SysUser;
import com.nnutc.system.custom.LoginUser;
import com.nnutc.system.service.SysUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysUserService sysUserService;

    //    实现自定义的过滤器逻辑，用于处理请求和响应。这个方法在每个请求到达被过滤器链时被调用。
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("jwt过滤器执行");
//        获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
//            放行
            filterChain.doFilter(request, response);
            return;
        }
//        解析token
        String userid;
        try {
             userid = JwtHelper.getUserId(token);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
//        从redis中获取用户信息
        String redisKey = "login:"+userid;
        LoginUser loginUser =
                (LoginUser) redisTemplate.opsForValue().get(redisKey);
        System.out.println("redis中取出loginUser:"+loginUser);
        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
//        获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
//        存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        放行
        filterChain.doFilter(request, response);
    }
}
