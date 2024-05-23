package com.nnutc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnutc.model.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Schema(description = "所属上级")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "标题")
    @TableField("title")
    private String title;

    @Schema(description = "类型(1:菜单,2:按钮)")
    @TableField("type")
    private Integer type;

    @Schema(description = "路由地址")
    @TableField("path")
    private String path;

    @Schema(description = "组件路径")
    @TableField("component")
    private String component;

    @Schema(description = "权限标识")
    @TableField("perms")
    private String perms;

    @Schema(description = "图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "排序")
    @TableField("sort_value")
    private Integer sortValue;

    @Schema(description = "状态(0:禁止,1:正常)")
    @TableField("status")
    private Integer status;
    @Schema(description = "状态(0:隐藏,1:显示)")
    @TableField("hidden")
    private Integer hidden;
    // 下级列表
    @TableField(exist = false)
    private List<SysMenu> children;
    //是否选中
    @TableField(exist = false)
    private boolean isSelect;
}

class MetaVo {
    private String title;
    private String icon;
    private boolean hidden;

    public MetaVo(String title, String icon, boolean hidden) {
        this.title = title;
        this.icon = icon;
        this.hidden = hidden;
    }

    // Getters and setters
}

class RouterVo {
    private String name;
    private String path;
    private String component;
    private MetaVo meta;
    private List<RouterVo> children = new LinkedList<>();


    }

