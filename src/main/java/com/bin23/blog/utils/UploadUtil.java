package com.bin23.blog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UploadUtil {
    public static String genNewFilename(MultipartFile file){
        // 获取整体文件名，原名称
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        // 多生成两位随机数，方便文件名称后面多追加两位数字
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFilename = tempName.toString();
        return newFilename;
    }
}
