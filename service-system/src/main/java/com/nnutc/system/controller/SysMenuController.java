package com.nnutc.system.controller;

import com.nnutc.common.result.Result;
import com.nnutc.model.entity.SysMenu;
import com.nnutc.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/sysMenu")
@Tag(name = "菜单管理", description = "菜单管理相关接口")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    //    菜单列表(树形)
    @Operation(summary = "菜单列表")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    //    添加菜单
    @Operation(summary = "添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu sysMenu) {
        boolean flag = sysMenuService.save(sysMenu);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //根据id查询
    @Operation(summary="根据id查询菜单")
    @GetMapping("findNode/{id}")
    public Result findNode(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return  Result.ok(sysMenu);
    }

    //修改
    @Operation(summary="修改菜单")
    @PostMapping("update")
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }

    //删除菜单
    @Operation(summary="删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        sysMenuService.removeMenuById(id);
        return Result.ok();
    }
}
