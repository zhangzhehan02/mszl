package com.mszlu.blog.handler;

import com.mszlu.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
* 对加了controller的注解进行拦截处理  AOP的实现
*
* */
@ControllerAdvice
public class AllExceptionHandler {

    //进行异常处理,处理Exception.class的异常
   @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
       ex.printStackTrace();
       return Result.fail(-999,"系统异常");
   }
}
