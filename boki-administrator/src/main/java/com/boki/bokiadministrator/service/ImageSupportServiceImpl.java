package com.boki.bokiadministrator.service;

import com.boki.bokiadministrator.service.inter.ImageSupportService;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: LJF
 * @Date: 2020/3/16
 * @Description:
 */
@Slf4j
@Service
public class ImageSupportServiceImpl implements ImageSupportService {

    /**
     * http://服务器域名/image/
     */
    @Value("${base.serverHost}")
    private String serverHost;

    /**
     * 硬盘的绝对路径
     */
    @Value("${base.actualPath}")
    private String actualPath;

    @Override
    public String saveImage(MultipartFile file) {
        // 获取文件名
        int lastIndexOfPoint = file.getOriginalFilename().lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(lastIndexOfPoint);
        //用md5生成文件名,然后加个时间戳前缀
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                + "_" + DigestUtils.md5DigestAsHex(file.getOriginalFilename().getBytes()) + suffix;
        String path = actualPath + fileName;
        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists()) {
            throw new BusinessException().setType(RequestResultCode.EXIST_FILE);
        }
        //父目录不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        String url;
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            if (ImageIO.read(dest) == null) {
                dest.delete();
                throw new BusinessException("非图片格式.").setType(RequestResultCode.FAIL);
            }
            url = serverHost + fileName;
        } catch (IOException e) {
            log.warn("文件保存失败,原因是"+e.getMessage());
            throw new BusinessException(e.getMessage()).setType(RequestResultCode.FAIL);
        }
        return url;
    }
}
