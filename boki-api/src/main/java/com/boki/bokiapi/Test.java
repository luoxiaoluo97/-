package com.boki.bokiapi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */

public class Test {
    public static void main(String[] args) throws IOException {


        BufferedImage bi = ImageIO.read(new File("D:\\temporary\\1608010125林峻锋 毕业论文.jpg"));
        if(bi == null){
            System.out.println("false");
        }


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
