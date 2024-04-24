package com.nnutc.system.exception;


import com.nnutc.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    //1 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json格式数据
    public Result error(Exception e) {
        System.out.println("全局....");
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

    //2 特定异常处理
    // 除数异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        System.out.println("特定......");
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理");
    }

    //3 自定义异常处理
    @ExceptionHandler(ToolException.class)
    @ResponseBody
    public Result error(ToolException e) {
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

}
