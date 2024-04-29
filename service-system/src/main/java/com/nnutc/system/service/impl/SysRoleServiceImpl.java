package com.nnutc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnutc.model.entity.SysRole;
import com.nnutc.model.entity.SysUserRole;
import com.nnutc.system.mapper.SysUserRoleMapper;
import com.nnutc.system.service.SysRoleService;
import com.nnutc.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author admin
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2024-04-22 09:13:11
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        //获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        //根据用户id查询，已经分配角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        //从userRoles集合获取所有角色id
        List<String> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole:userRolesList) {
            String roleId = String.valueOf(userRole.getRoleId());
            userRoleIds.add(roleId);
        }
        //封装到map集合
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",roles);//所有角色
        returnMap.put("userRoleIds",userRoleIds);//用户分配角色id集合
        return returnMap;
    }
}




