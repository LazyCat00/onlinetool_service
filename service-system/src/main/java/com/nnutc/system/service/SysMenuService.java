package com.nnutc.system.service;

import com.nnutc.model.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2024-04-28 22:38:15
*/
public interface SysMenuService extends IService<SysMenu> {

    //    菜单列表(树形)
    List<SysMenu> findNodes();

    void removeMenuById(String id);
}
