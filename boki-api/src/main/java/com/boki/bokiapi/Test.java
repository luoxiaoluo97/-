package com.boki.bokiapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */

public class Test {
    public static void main(String[] args) throws IOException {

//        Pattern.compile(".*[\\S]+.*").matcher("    ").matches();

        List<String> a = new ArrayList();
        a.add("123");
        a.add("321");
        a.add("123");
        a = a.stream().distinct().collect(Collectors.toList());
        System.out.println(new Date().toString());

//        String source = "回复 @我日你仙人板板1 :回复 @我日你仙人板板2 :回复 @我日你仙人板板3 :";
//        Matcher matcher = Pattern.compile("@[^@ ]{1,12}[ ]").matcher(source);
//        matcher.replaceFirst("");
//        while (matcher.find()) {
//            System.out.println(matcher.start());
//            String s = matcher.group();
//            System.out.println(s.substring(1,s.length()-1));
//        }
    }
}
