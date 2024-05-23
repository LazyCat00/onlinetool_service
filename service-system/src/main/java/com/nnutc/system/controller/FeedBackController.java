package com.nnutc.system.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nnutc.common.result.Result;
import com.nnutc.model.entity.SysFeedback;
import com.nnutc.model.entity.SysMenu;
import com.nnutc.model.entity.SysUser;
import com.nnutc.system.custom.LoginUser;
import com.nnutc.system.mapper.SysFeedbackMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/feedback")
public class FeedBackController {
@Autowired
    SysFeedbackMapper sysFeedbackMapper;

    @Operation(summary = "登录", description = "Get user details by their ID")
    @GetMapping("query")
    public Result query() {
        System.out.println("/feedback/query接口被请求！");
        QueryWrapper<SysFeedback> wrapper = new QueryWrapper<>();
        wrapper.eq("hidden",0);
        List<SysFeedback> sysFeedbacks = sysFeedbackMapper.selectList(wrapper);

        return Result.ok(sysFeedbacks).message("查询成功");
    }

    @GetMapping("queryAll")
    public Result queryAll() {
        System.out.println("/feedback/queryAll接口被请求！");
        List<SysFeedback> sysFeedbacks = sysFeedbackMapper.selectList(null);

        return Result.ok(sysFeedbacks).message("查询成功");
    }

    @PostMapping("update")
    public Result update(@RequestBody SysFeedback sysFeedback) {
        System.out.println("/feedback/update接口被请求！");
        int is_success = sysFeedbackMapper.updateById(sysFeedback);
        if (is_success==1) {
            return Result.ok("feedback修改成功！");
        } else {
            return Result.fail("feedback修改失败！");
        }
    }
}
