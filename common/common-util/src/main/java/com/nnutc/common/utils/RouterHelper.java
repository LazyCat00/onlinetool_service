package com.nnutc.common.utils;

import com.nnutc.model.entity.SysMenu;
import com.nnutc.model.vo.MetaVo;
import com.nnutc.model.vo.RouterVo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据菜单数据构建路由的工具类
 */
public class RouterHelper {

    /**
     * 根据菜单构建路由
     * @param menus 菜单列表
     * @return 构建后的路由列表
     */
    public static List<RouterVo> buildRouters(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<>(); // 存储构建后的路由对象
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            // 设置路由路径
            router.setPath(getRouterPath(menu));
            // 设置组件路径
            router.setComponent(menu.getComponent());
            // 设置路由名称
            router.setName(menu.getName());

            // 设置路由元信息
            if (menu.getHidden() == 0) {
                // 0表示隐藏
                router.setMeta(new MetaVo(menu.getTitle(), menu.getIcon(), true));
            } else if (menu.getHidden() == 1) {
                // 1表示显示
                router.setMeta(new MetaVo(menu.getTitle(), menu.getIcon(), false));
            }

            // 获取子菜单列表，确保不为null
            List<SysMenu> children = menu.getChildren();
            if (children == null) {
                children = new LinkedList<>();
            }

            // 递归处理子菜单
            List<RouterVo> childRouters = buildRouters(children);
            router.setChildren(childRouters);

            // 添加当前路由到路由列表
            routers.add(router);
        }
        return routers;
    }


    /**
     * 获取路由地址
     * @param menu 菜单信息
     * @return 路由地址
     */
    public static String getRouterPath(SysMenu menu) {
        // 生成路由路径，根节点路径以"/"开头
        String routerPath = "/" + menu.getPath();
        // 如果不是根节点，则直接返回路径
        // if (menu.getParentId().intValue() != 0) {
        //     routerPath = menu.getPath();
        // }
        return routerPath;
    }
}
