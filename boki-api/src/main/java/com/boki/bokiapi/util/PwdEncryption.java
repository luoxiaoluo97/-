package com.boki.bokiapi.util;

import org.springframework.util.DigestUtils;

/**
 * @Author: LJF
 * @Date: 2020/2/20
 * @Description: MD5双重加密
 */
public class PwdEncryption {

    /**
     * 加密后取倒数第2~5位，作为原密码的盐值，再加密
     * @param pwd
     * @return
     */
    public static String doubleMd5(String pwd) {
        String md5 = DigestUtils.md5DigestAsHex(pwd.getBytes());
        pwd = pwd + md5.substring(md5.length() - 5, md5.length() - 1);
        return DigestUtils.md5DigestAsHex(pwd.getBytes());
    }

}
