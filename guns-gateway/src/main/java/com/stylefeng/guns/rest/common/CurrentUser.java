package com.stylefeng.guns.rest.common;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/7
 */
public class CurrentUser {

    // 线程绑定的存储空间
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // 将用户信息放入存储空间
    public static void saveUUID(String uuid) {
        threadLocal.set(uuid);
    }

    // 将用户信息取出
    public static String getCurrentUser() {
        return threadLocal.get();
    }
}
