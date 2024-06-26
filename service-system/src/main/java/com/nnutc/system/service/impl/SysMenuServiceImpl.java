package com.nnutc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnutc.common.utils.MenuHelper;
import com.nnutc.common.utils.RouterHelper;
import com.nnutc.model.entity.SysMenu;
import com.nnutc.model.vo.RouterVo;
import com.nnutc.system.exception.ToolException;
import com.nnutc.system.service.SysMenuService;
import com.nnutc.system.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author admin
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-04-28 22:38:15
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        //所有菜单数据转换要求数据格式
        List<SysMenu> resultList = MenuHelper.bulidTree(sysMenuList);
        return resultList;
    }

    //删除菜单
    @Override
    public void removeMenuById(String id) {
        //查询当前删除菜单下面是否子菜单
        //根据id = parentid
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = Math.toIntExact(baseMapper.selectCount(wrapper));
        if(count > 0) {//有子菜单
            throw new ToolException(201,"请先删除子菜单");
        }
        //调用删除
        baseMapper.deleteById(id);
    }

    //根据userid查询按钮权限值
    @Override
    public List<String> getUserPermission(String userId) {
        List<SysMenu> sysMenuList = null;
        //判断是否管理员
        if("1".equals(userId)) {
            sysMenuList =
                    baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1));
        } else {
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }
        //sysMenuList遍历
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu:sysMenuList) {
            // type=2
            if(sysMenu.getType()==2) {
                String perms = sysMenu.getPerms();
                permissionList.add(perms);
            }
        }
        return permissionList;
    }

    @Override
    public List getUserMenuList(String userId) {
        //admin是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;
        List menuList = new ArrayList();
        //判断userid值是1代表超级管理员，查询所有权限数据
        if("10001".equals(userId)) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status",1);
            wrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            //如果userid不是10001，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }


        sysMenuList.forEach(sysMenu -> menuList.add(sysMenu.getName()));

        return menuList;
    }

    @Override
    public List<String> getUserButtonList(String id) {
        return null;
    }

}




