package com.nnutc.system.custom;

import com.alibaba.fastjson2.annotation.JSONField;
import com.nnutc.model.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {
    private SysUser sysUser;
    private List<String> permissions;

//    存储springsecurity所需要的权限信息集合
    @JSONField(serialize = false)//不进行序列化
    private List<GrantedAuthority> authorities;

    public LoginUser(SysUser sysUser, List<String> userPermission) {
        this.sysUser = sysUser;
        this.permissions =userPermission;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authorities != null) {
            return authorities;
        }
        authorities = new ArrayList<>();
//        把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
