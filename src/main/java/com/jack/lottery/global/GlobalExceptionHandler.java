package com.jack.lottery.global;

import com.jack.lottery.exception.BusinessException;
import com.jack.lottery.util.TResult;
import com.jack.lottery.util.TResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 天宇
 * 全局controller advice，捕捉异常，并包装结果
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public TResult handleException(HttpServletRequest req, Exception e) {
        LOGGER.warn(e.getMessage());
        return TResult.failure(e.getMessage());
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public TResult handleBusinessException(HttpServletRequest req, BusinessException e) {
        return TResult.failure(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public TResult handleNullPointerException(HttpServletRequest req, Exception e) {
        return TResult.failure(TResultCode.RESULE_DATA_NONE);
    }

    /**
     * 参数绑定错误时，将message传回给前台
     */
    @ExceptionHandler(BindException.class)
    public TResult handleBindException(BindException e) {
        return TResult.failure(Arrays.toString(e.getAllErrors().stream().map(ObjectError::getDefaultMessage).toArray()));
    }

    /**
     * 验证参数异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public TResult handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        LOGGER.info("MethodArgumentNotValidException",e);

        String errorMesssage = "";
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ";";
        }
        return TResult.failure(errorMesssage);
   }
}
