package com.nnutc.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nnutc.common.result.Result;
import com.nnutc.model.entity.SysUser;
import com.nnutc.model.vo.SysUserQueryVo;
import com.nnutc.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author admin
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    //    用户列表
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page, @PathVariable Long limit, SysUserQueryVo sysUserQueryVo) {
//        创建page对象
        Page<SysUser> pageParam = new Page<>(page, limit);
//        调用service方法
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, sysUserQueryVo);
        return Result.ok(pageModel);
    }

    //    添加用户
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser) {
        boolean is_success = sysUserService.save(sysUser);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }


    //    根据id查询
    @GetMapping("getUser/{id}")
    public Result getUser(@PathVariable String id) {
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }

    //    修改用户
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        boolean is_success = sysUserService.updateById(sysUser);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    删除用户
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        boolean is_success = sysUserService.removeById(id);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }


//    测试
    @GetMapping("/list")
    public List<SysUser> getList() {
        return sysUserService.list();
    }
}

