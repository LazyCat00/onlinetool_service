package com.nnutc.system.controller;

import com.alibaba.fastjson2.JSON;
import com.nnutc.common.result.Result;
import com.nnutc.common.utils.JwtHelper;
import com.nnutc.model.entity.SysUser;
import com.nnutc.model.vo.LoginVo;
import com.nnutc.system.custom.LoginUser;
import com.nnutc.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    //login
    //{"code":20000,"data":{"token":"admin-token"}}

    @Operation(summary = "登录", description = "Get user details by their ID")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        System.out.println("登录接口被请求！ loginVo"+ JSON.toJSONString(loginVo));
//
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(),loginVo.getPassword());
////        authenticationManager最终调用DBUserDetailsManager进行用户校验
        Authentication authenticate = authenticationProvider.authenticate(authenticationToken);
//
        System.out.println("authenticationToken"+authenticationToken);
////        如果认证不通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
//        如果认证通过
        LoginUser loginUser =(LoginUser) authenticate.getPrincipal();
        System.out.println("loginUser:"+loginUser);
        String userId = loginUser.getSysUser().getId().toString();
        String username = loginUser.getUsername();
        //根据userid和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(userId, username);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        //保存权限数据
        redisTemplate.opsForValue().set("login:"+userId,loginUser);

        return Result.ok(map);
    }

    //info
//    {"code":20000,"data":{"roles":["admin"],
//        "introduction":"I am a super administrator",
//         "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
//         "name":"Super Admin"}}
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        System.out.println("info");
        //获取请求头token字符串
        String token = request.getHeader("token");

        //从token字符串获取用户名称（id）
        String username = JwtHelper.getUsername(token);

        //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    @GetMapping("test")
    public Result test(@Parameter(in = ParameterIn.HEADER, description = "Authorization token", required = true) String token) {

        System.out.println("test");

        return Result.ok();
    }

}
