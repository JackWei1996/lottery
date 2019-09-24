package com.jack.lottery.util;

import com.jack.lottery.global.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InterceptorUtil {
    /**
     * 允许跨域
     */
    public static void allowCORS(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "5000");
        response.setHeader("Access-Control-Allow-Headers", "*");
    }

    /**
     * 检查session中是否存在对应的角色
     */
    public static boolean preCheck(HttpServletRequest request, HttpServletResponse response, String targetRole) throws IOException {
        InterceptorUtil.allowCORS(response);

//        这里判断是否admin角色
        String role = (String) request.getSession().getAttribute(Constant.KEY_ROLE);
        if (role == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "请先登录");
            return false;
        }
        if (!role.equals(targetRole)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Business permission deny");
            return false;
        }
        return true;
    }
}
