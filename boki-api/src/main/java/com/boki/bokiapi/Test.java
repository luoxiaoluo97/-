package com.boki.bokiapi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */

public class Test {
    public static void main(String[] args) {

        String source = "回复 @我日你仙人板板 :回复 @我日你仙人板板 :回复 @我日你仙人板板 :";
        Matcher matcher = Pattern.compile("回复[ ]@[^@ ]{1,12}[ ]:").matcher(source);

        if (matcher.find()) {
            System.out.println(matcher.start());
            System.out.println(matcher.group());
        }
    }
}
