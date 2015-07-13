package com.web.security.cache;

/**
 * Created by nlf on 2015-7-13.
 */
public class CacheNameSpace {
    /**
     * 登录验证信息缓存
     */
    public static final  String AUTHORIZATION_CACHE="authorizationCache";
    /**
     * 密码加密信息缓存
      */
    public static final  String PASSWORDRETRY_CACHE="passwordRetryCache";
    /**
     * 授权信息缓存
     */
    public static final  String AUTHENTICATION_CACHE="authenticationCache";
    /**
     * 会话信息缓存
     */
    public static final  String ACTIVESESSION_CACHE="shiro-activeSessionCache";
}
