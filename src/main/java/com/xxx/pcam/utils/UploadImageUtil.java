package com.xxx.pcam.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/*
 *上传图片所用到的工具类
 * */
public class UploadImageUtil {

    // 定义一个目标路径，就是我们要把图片上传的位置
    private static String BASE_PATH = "C:\\Users\\Administrator.DESKTOP-H4SEFSL\\Desktop\\推文\\原神\\";

    // 定义访问图片路径
    private static final String SERVER_PATH = "http://localhost:8080/pcam/doctor/upload";

    public static String upload(MultipartFile file) {
        // 获取上传图片的名称
        String filename = file.getOriginalFilename();
        // 为了保证图片在服务器中名字的唯一性，使用UUID来对filename进行改写
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 将生成的uuid和filename惊醒拼接
        String newFileName = uuid + '-' + filename;
        // 创建一个文件实例对象
        File image = new File(BASE_PATH, newFileName);
        // 对这个文件进行上传操作
        try {
            file.transferTo(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(SERVER_PATH);
        return SERVER_PATH + newFileName;
    }
}