package com.nnutc.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nnutc.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nnutc.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * @author admin
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2024-04-22 09:13:11
 */
public interface SysUserService extends IService<SysUser> {

    //    用户列表
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);


    //    根据用户名查询用户
    SysUser getUserInfoByUserName(String username);


    //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
    Map<String, Object> getUserInfo(String username);
}
