package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.FileSystemEntity;
import com.shenhua119.leadservice.service.FileSystemService;
import com.shenhua119.leadservice.util.Msg;
import com.shenhua119.leadservice.util.SerialNumber;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-18 1:12
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("filesystem")
public class FileServerController {

    @Value("${fastdfs-path.upload_location}")
    private String upload_location;

    @Autowired
    private FileSystemService fileSystemService;

    @PostMapping("/upload")
    @ResponseBody
    public Msg upload(@RequestParam("file") MultipartFile file) throws IOException {

        FileSystemEntity fileSystem = new FileSystemEntity();
        //
        String originalFilename =  file.getOriginalFilename();
        String extension  = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameNew = SerialNumber.Getnum() + extension;
        // 把文件暂时存储在本地服务器上
        File file1 = new File(upload_location +"\\"+ fileNameNew);
        file.transferTo(file1);
        // 获取新文件上传的物理路径
        String newFilePath = file1.getAbsolutePath();

        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);
            // 创建TrackerClient
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            // 定义 StorageClient
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            // 文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", originalFilename);
            // 执行上传
            String fileId = client.upload_file1(newFilePath, null, metaList);
            System.out.println("upload success. file id is: " + fileId);

            // 设置文件信息
            fileSystem.setFileId(fileId).setFilePath(fileId).setFileName(originalFilename)
                    .setFileType(extension).setFileSize(file.getSize()).setCreateDate(new Date());

            //保存文件信息
            boolean result = fileSystemService.save(fileSystem);

            // 关闭 trackerServer 连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Msg.success().add("fileSystem", fileSystem);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Msg delete(String file_id){

        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);
            // 创建TrackerClient
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            // 定义 StorageClient
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            // 执行上传
            int res = client.delete_file1(file_id);
            System.out.println("delete success. file id is: " + file_id);
            System.out.println("delete success. file number is: " + res);
            // 关闭 trackerServer 连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Msg.success();
    }

    // 获取所有文件数据
    @GetMapping("/all")
    @ResponseBody
    public Msg getAllList(){
        List<FileSystemEntity> list = fileSystemService.list(null);
        return Msg.success().add("list",list);
    }
}
