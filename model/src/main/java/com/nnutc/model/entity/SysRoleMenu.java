package com.nnutc.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nnutc.model.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色菜单
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
public class SysRoleMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Schema(description = "角色id")
    @TableField("role_id")
    private String roleId;
    @Schema(description = "菜单id")
    @TableField("menu_id")
    private String menuId;

}