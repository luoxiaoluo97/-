package com.boki.bokiadministrator.service.inter;


import org.springframework.web.multipart.MultipartFile;

public interface ImageSupportService {

    /**
     * 保存文件，返回url
     * @param file
     * @return
     */
    String saveImage(MultipartFile file);
}
