package com.javasoso.pass.controller;

import com.javasoso.pass.constant.ResultModel;
import com.javasoso.pass.exception.SuperPassException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * 异常处理
 * Created by jasonzhu on 2017/7/14.
 */
@ControllerAdvice
public class ErrorController extends BaseController{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 业务验证异常
     */
    @ExceptionHandler(value = SuperPassException.class)
    @ResponseBody
    public ResultModel authException(SuperPassException exception){
        logger.warn("业务验证异常：{}",exception.getMessage());
        return buildErrorResponse(exception.getMessage());
    }
    /**
     * 未知异常或参数异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel exception(Exception exception){
        logger.error("未预期异常",exception);
        if (exception.getClass() == BindException.class)
            return buildErrorResponse(((BindException)exception).getFieldError().getDefaultMessage());
        if (exception.getClass() == MethodArgumentNotValidException.class)
            return buildErrorResponse(((MethodArgumentNotValidException) exception).getBindingResult().getFieldError().getDefaultMessage());
        if(exception.getClass() == HttpMessageNotReadableException.class)
            return buildErrorResponse("提交的参数异常，请检查后再提交");
        if (exception.getClass() == SQLException.class)
            return buildErrorResponse("提交的参数不合法，请检查后再提交");
        if (exception.getClass() == IllegalArgumentException.class)
            return buildErrorResponse(exception.getMessage());
        return buildErrorResponse("操作失败，请检查后再重试");
    }

}
