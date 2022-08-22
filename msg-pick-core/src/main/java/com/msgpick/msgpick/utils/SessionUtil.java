package com.msgpick.msgpick.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtil {

    public final static String REGISTER_SHOP_INFO = "REGISTER_SHOP_INFO";
    public final static String REGISTER_SHOP_IMG_INFO = "REGISTER_SHOP_IMG_INFO";
    public final static String REGISTER_PROGRAM_INFO = "REGISTER_PROGRAM_INFO";
    public final static String REGISTER_THERAPIST_INFO = "REGISTER_THERAPIST_INFO";

    /**
     * attribute 값을 가져 오기 위한 method
     */
    public static Object getAttribute(String name) throws Exception {
        return RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * attribute 설정 method
     */
    public static void setAttribute(String name, Object object) throws Exception {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 설정한 attribute 삭제
     */
    public static void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * session id
     */
    public static String getSessionId() throws Exception  {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }

}
