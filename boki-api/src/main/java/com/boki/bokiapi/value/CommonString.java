package com.boki.bokiapi.value;

/**
 * @Author: LJF
 * @Date: 2020/2/23
 * @Description:
 */
public class CommonString {
    /**
     * 邮箱正则
     */
    public static final String PATTERN_MAIL = "\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*";

    /**
     * 防水贴缓存关键字
     */
    public static final String WAITING = "waiting";

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 艾特机制正则
     */
    public static final String STOREY_CALL = "@*{1,12}[ ]";

}
