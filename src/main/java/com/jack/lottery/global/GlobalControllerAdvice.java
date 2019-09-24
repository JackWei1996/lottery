package com.jack.lottery.global;

import com.alibaba.fastjson.JSON;
import com.jack.lottery.util.TResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author 天宇
 * 全局controller advice
 * 包装请求成功的返回结果
 */
@RestControllerAdvice(basePackages = "cn.tianyustudio.musicpartner_api.controller")
public class GlobalControllerAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 当发生异常时，会在全局异常处理类里进行包装，这里不用再进行JSON格式的包装。
        // 也因为这句话，TResult类的返回格式不会再经由此类处理
        toLog();
        return !methodParameter.getGenericParameterType().getTypeName().equals(TResult.class.getTypeName());
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        TResult result = TResult.success(o);
        toLog();
        // (1)如果方法的返回结果是String类，则会调用方法的返回值，调用StringMessageConverter。
        //    而这个Convert只接收String类型的参数，所以再转换成其他类，Convert处理时会出错，所以这里再将TResult转换为String
        // (2)另外一种解决方案是重写StringMessageConvert或添加自己的Convert
        if (o instanceof String) {
            serverHttpResponse.getHeaders().setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)) ;
            return JSON.toJSON(result);
        }
        return result;
    }

    private void toLog(){
    }
}