package com.jack.lottery.global;

import com.alibaba.fastjson.JSON;
import com.jack.lottery.util.JwtUtil;
import com.jack.lottery.util.SpringBeanFactoryUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 天宇
 * 请求参数、响应体统一日志打印
 */
@Aspect
@Component
public class RestControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 环绕通知
     *
     * @param joinPoint 连接点
     * @return 切入点返回值
     * @throws Throwable 异常信息
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController) " +
            "|| @annotation(org.springframework.web.bind.annotation.RestController)")
    public Object apiLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("********* api log *********");
        logger.info("url: " + request.getMethod() + " " + request.getRequestURI());
        logger.info("method: " + joinPoint.getSignature().toString());
        // 去除敏感字段后的parameter map
        logger.info("parameter map: " + JSON.toJSON(deleteSensitiveContent(request.getParameterMap())));
        logger.info("user-agent: " + request.getHeader("user-agent"));
        logger.info("remote ip: " + request.getRemoteAddr() + ", port: " + request.getRemotePort());
        logger.info("request time: " + new Date());
        return joinPoint.proceed();
    }

    /**
     * 获取ip地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 删除参数中的敏感内容
     *
     * @return 去除敏感内容后的参数对象
     */
    private Map<String, String[]> deleteSensitiveContent(Map<String, String[]> parameterMap) {
        HashMap<String, String[]> resMap = new HashMap<>(16);
        resMap.putAll(parameterMap);
        getSensitiveFieldList().forEach(s -> {
            if (resMap.containsKey(s)) {
                resMap.put(s, new String[]{"******"});
            }
        });
        return resMap;
    }

    /**
     * 敏感字段列表（当然这里你可以更改为可配置的）
     */
    private List<String> getSensitiveFieldList() {
        List<String> sensitiveFieldList = new ArrayList<>();
        sensitiveFieldList.add("pwd");
        sensitiveFieldList.add("password");
        sensitiveFieldList.add("oldPassword");
        sensitiveFieldList.add("newPassword");
        sensitiveFieldList.add("oldPwd");
        sensitiveFieldList.add("newPwd");
        return sensitiveFieldList;
    }
}
