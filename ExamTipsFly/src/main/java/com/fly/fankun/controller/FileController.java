package com.fly.fankun.controller;

import cn.hutool.core.date.DateUtil;
import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2020/3/5
 */
@RestController
@Slf4j
@RequestMapping("/file")
@Api(value = "文件管理", tags = "文件管理")
public class FileController {

    @Value("${upload.filePath}")
    private String filePath;

//    @ApiOperation(value = "普通上传")
//    @PostMapping("/real/uploadFile")
//    public BaseResult uploadFile(
//        @RequestParam("file") MultipartFile file, HttpServletRequest request) {
//
//        try {
//            //定义相对路径
//            String relativePath = File.separator+ DateUtil.format(new Date(),"yyyy-MM-dd" )+File.separator;
//            String path = filePath + relativePath;
//            String fileName = file.getOriginalFilename();
//            String uuid = UUID.randomUUID().toString().replaceAll("-","");
//            String suffix = fileName.substring(fileName.lastIndexOf("."));
//
//
//            //随机生成新的文件名，防止文件名冲突或者中文乱码问题
//            String newFileName = uuid+suffix;
//
//            //调用上传方法将文件上传到物理路径下
//            FileUtils.uploadFile(file.getBytes(),path,newFileName);
//
//            //可选：将图片路径存储起来为了定期清理图片（可以存储到非关系型数据库中，如mongodb）
//
//            //返回虚拟路径
//            return  BaseResult.success(filePath+relativePath+newFileName);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    @PostMapping("/uploadFile")
    public BaseResult uploadFile1(
        @RequestParam("file") MultipartFile file) {
            //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String fileName = file.getOriginalFilename();  //获取文件名

        // 图片存储目录及图片名称
        String urlPath = File.separator + fileName;
        //图片保存路径
        String savePath = staticPath + File.separator + urlPath;
        System.out.println("图片保存地址："+savePath);
        // 访问路径=静态资源路径+文件目录路径
        String visitPath ="static/" + urlPath;
        System.out.println("图片访问uri："+visitPath);

        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BaseResult.success(visitPath);
    }

}
