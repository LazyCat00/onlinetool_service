package com.nnutc.system.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nnutc.model.entity.SysUser;
import com.nnutc.system.custom.LoginUser;
import com.nnutc.system.mapper.SysUserMapper;
import com.nnutc.system.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    //    从数据库中获取用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("重写loadUserByUsername方法执行！");
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (Objects.isNull(sysUser)) {
            throw new UsernameNotFoundException(username);
        } else {
            List<String> userPermission = sysMenuService.getUserPermission(sysUser.getId());
            return new LoginUser(sysUser,userPermission);
        }
    }

}
