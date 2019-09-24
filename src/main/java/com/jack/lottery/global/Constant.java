package com.jack.lottery.global;

/**
 * 系统常量
 */
public interface Constant {

    int YES = 1;
    int NO = 0;

    String QINIU_ACCESSKEY = "n1k2it8VJz-Z562jA7_7WsViAkYbGQ1vNJUBd5dv";
    String QINIU_SECRETKEY = "aAPZcX4u21Uu52i4GUu3pgviAzeRjoFWyWdyC43C";
    String QINIU_BUCKET = "music-partner";
    String QINIU_URL ="http://pxnlj5q05.bkt.clouddn.com";

    String QINIU_URL_Default_BgImg = "";

    /**
     * token 有效期
     */
    long TOKEN_MAX_VALID_TIME = 15 * 24 * 60 * 60 * 1000;

    /**
     * JWT 请求头
     */
    String AUTHORIZATION = "AUTHORIZATION";

    /**
     * jwt验证头，此字符串代表使用jwt验证
     */
    String BEARER = "Bearer";
    /**
     * jwt payload的key
     */
    String KEY_ID = "id";
    String KEY_OPEN_ID = "openid";
    String KEY_ROLE = "role";

    String ROLE_USER = "user";
    String ROLE_ADMIN = "admin";


    String WX_APPID = "wxbad30ed37c3c3be2";
    String WX_APPSECRET = "4ea8bf74381f1ecbea7757659c5139bb";

    int SMS_APPID = 1400261250;
    String SMS_APPKEY = "0bdcb805eb358c8b50eec4ab31ab6b6c";
    int SMS_TEMPLATE_ID = 427887;
    int SMS_SEND_MAX = 5;
    String SMS_VALIDITY_MINUTE = "2";

    /**
     * 腾讯云签名内容
     */
    String SMS_SIGN = "左邻右伙";
    /**
     * 腾讯云短信签名id，应该用不到
     */
    int SMS_SIGN_ID = 184893;
}
