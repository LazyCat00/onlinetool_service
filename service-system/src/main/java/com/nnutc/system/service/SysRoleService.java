package com.nnutc.system.service;

import com.nnutc.model.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author admin
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2024-04-22 09:13:11
*/
public interface SysRoleService extends IService<SysRole> {

    //获取用户的角色数据
    Map<String, Object> getRolesByUserId(String userId);
}
