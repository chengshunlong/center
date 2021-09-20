package com.zhbit.controller;

import com.zhbit.exception.MyDatabaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 集中处理异常、加入到ioc容器中
 * @Description
 * @Author livestrong
 * @Date 2021-05-14-12:55
 */
@ControllerAdvice
public class MyExceptionController {

    /**
     * 告诉SpringMVC专门处理当前这个类发生的异常
     *
     * 方法参数中的Exception exception是用来接收异常信息，携带异常信息不能写model
     * 或者直接返回ModelAndView
     */
    @ExceptionHandler(value = {ArithmeticException.class})//数学异常
//    public String handleException01(Exception exception){
    public ModelAndView arithmeticException(Exception exception){

        ModelAndView modelAndView = new ModelAndView("exceptionHandle");

        modelAndView.addObject("exceptionInfo",exception);

        return modelAndView;
        //视图解析器拼串
//        return "myError";
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView nullPointerException(Exception exception){
        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
        modelAndView.addObject("exceptionInfo",exception);
        return modelAndView;
    }


    @ExceptionHandler(value = {Exception.class})
    public ModelAndView exception(Exception exception){
        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
        modelAndView.addObject("exceptionInfo",exception);
        return modelAndView;
    }


}
