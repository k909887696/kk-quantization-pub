package com.kk.quantizationapi.handler;

import com.kk.quantizationapi.common.ApiResult;
import com.kk.quantizationapi.common.exception.BusinessException;
import com.kk.quantizationapi.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Author: kk
 * @Date: 2021/11/18 14:41
 * 异常统一处理器
 */
@RestControllerAdvice
public class MyExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    /**
     * 系统异常拦截
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Object validationException(HttpServletRequest request, Exception ex)
    {
        logger.error("{}|{}","validationException;Message",ex.getMessage());
        logger.error("{}|{}","validationException;StackTrace",ex.getStackTrace());
        ApiResult result;
        if (ex instanceof BusinessException) {
            BusinessException b = (BusinessException)ex;
            result = new ApiResult(b.getCode(), b.getMessage());
        } else {
            result = new ApiResult(ResponseCode.SYSTEM_EXCEPTION.getCode(),
                    ResponseCode.SYSTEM_EXCEPTION.getDesc()+":"+ex.getMessage());
        }
        return result;

    }
    /**
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validationBodyException(MethodArgumentNotValidException exception){

        BindingResult result = exception.getBindingResult();
        StringBuilder message = new StringBuilder();
          if (result.hasErrors()) {
              List<ObjectError> errors = result.getAllErrors();
              errors.forEach(p ->{
                  FieldError fieldError = (FieldError) p;

                  message.append(fieldError.getDefaultMessage());
                  logger.error("{}|{}","validationBodyException;Data check failure","object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                          "},errorMessage{"+fieldError.getDefaultMessage()+"}");
              });
          }
        logger.error("{}|{}","validationBodyException;Message",exception.getMessage());
        return new ApiResult(ResponseCode.BUSINESS_PARAMETER_EXCEPTION.getCode(),message.toString());
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public Object parameterTypeException(HttpMessageConversionException exception){
        logger.error("{}|{}","parameterTypeException",exception.getCause().getLocalizedMessage());
        return  new ApiResult(ResponseCode.INVALID_PARAMETER.getCode(),ResponseCode.INVALID_PARAMETER.getDesc()+":"+exception.getCause().getLocalizedMessage());
    }

}
