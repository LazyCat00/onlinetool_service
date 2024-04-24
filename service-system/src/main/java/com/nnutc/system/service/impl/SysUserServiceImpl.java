package com.nnutc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.nnutc.model.entity.SysUser;
import com.nnutc.model.vo.SysUserQueryVo;
import com.nnutc.system.service.SysUserService;
import com.nnutc.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author admin
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2024-04-22 09:13:11
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam,sysUserQueryVo);
    }

    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);
    }

//    根据用户名获取用户基本信息
    @Override
    public Map<String, Object> getUserInfo(String username) {
//        根据username查询用户基本信息
        SysUser sysUser = this.getUserInfoByUserName(username);
        Map<String,Object> result = new HashMap<>();
        result.put("name",username);
        return null;
    }
}




