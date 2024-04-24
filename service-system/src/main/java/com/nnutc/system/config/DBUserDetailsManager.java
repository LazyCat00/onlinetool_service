package com.nnutc.system.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nnutc.model.entity.SysUser;
import com.nnutc.system.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;


public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    private SysUserMapper sysUserMapper;

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
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

          return   new User(sysUser.getUsername(),
                    sysUser.getPassword(),
                    true,//是否启用
                    true,//用户账号是否过期
                    true,//用户凭证是否过期
                    true,//用户是否未被锁定
                    authorities
            );
        }
    }
}
