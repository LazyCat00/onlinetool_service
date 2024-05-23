package com.nnutc.model.vo;

import lombok.Data;

/**
 * 路由显示信息
 *
 */
@Data
public class MetaVo
{
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;


    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    public MetaVo()
    {
    }

    public MetaVo(String title, String icon,Boolean hidden)
    {
        this.title = title;
        this.icon = icon;
        this.hidden =hidden;
    }

}

