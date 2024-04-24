package com.nnutc.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.nnutc.model.base.BaseEntity;
import lombok.Data;
import java.util.List;
/**
 * 角色
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableField("role_name")
    private String roleName;

    @TableField("role_code")
    private String roleCode;

    @TableField("description")
    private String description;

}