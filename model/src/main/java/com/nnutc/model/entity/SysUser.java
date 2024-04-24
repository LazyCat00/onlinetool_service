package com.nnutc.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.List;

import com.nnutc.model.base.BaseEntity;
import lombok.Data;

/**
 * 用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("head_url")
    private String headUrl;

    @TableField("dept_id")
    private Long deptId;

    @TableField("post_id")
    private Long postId;

    @TableField("description")
    private String description;

    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private List<SysRole> roleList;
    //岗位
    @TableField(exist = false)
    private String postName;
    //部门
    @TableField(exist = false)
    private String deptName;
}