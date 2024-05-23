package com.nnutc.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.nnutc.model.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName sys_feedback
 */

@TableName(value ="sys_feedback")
@Data
public class SysFeedback extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Schema(description = "内容")
    @TableField("content")
    private String content;
    @Schema(description = "邮箱")
    @TableField("mail")
    private String mail;
    @Schema(description = "反馈类型")
    @TableField("type")
    private String type;

    @Schema(description = "工具名称")
    @TableField("tool")
    private String tool;

    @Schema(description = "状态")
    @TableField("status")
    private String status;

    @Schema(description = "是否隐藏(0:隐藏,1:显示)")
    @TableField("hidden")
    private Integer hidden;
}