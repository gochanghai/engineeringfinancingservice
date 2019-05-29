package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhua119.leadservice.entity.FileSystem;
import com.shenhua119.leadservice.service.FileSystemService;
import com.shenhua119.leadservice.util.Msg;
import com.shenhua119.leadservice.util.SerialNumber;
import com.shenhua119.leadservice.vo.FileListVO;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

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

    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public Msg upload(@RequestParam("file") MultipartFile file) throws IOException {

        FileSystem fileSystem = new FileSystem();
        //
        String originalFilename =  file.getOriginalFilename();
        String extension  = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileNameNew = SerialNumber.Getnum() + "." + extension;
        // 把文件暂时存储在本地服务器上
        File file1 = new File(upload_location +"\\"+ fileNameNew);
        file.transferTo(file1);
        // 获取新文件上传的物理路径
        String newFilePath = file1.getAbsolutePath();

        try {
            // 初始化
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);
            // 创建tracker
            TrackerClient tracker = new TrackerClient();
            // 获取tracker服务
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            // 存储连接
            StorageClient1 storageClient  = new StorageClient1(trackerServer, storageServer);
            // 文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", originalFilename);
            // 上传返回结果
            String fileId = storageClient .upload_file1(newFilePath, null, metaList);
            System.out.println("upload success. file id is: " + fileId);

            // 设置文件信息
            fileSystem.setFileUrl(fileId)
                    .setFilePath(fileId)
                    .setFileName(originalFilename)
                    .setFileType(extension)
                    .setStatus(0)
                    .setFileSize(setSize(file.getSize()));
            //保存文件信息
            boolean result = fileSystemService.save(fileSystem);
            // 删除文件
            file1.delete();
            // 关闭 trackerServer 连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Msg.success().add("fileSystem", fileSystem);
    }

    @PostMapping("download")
    public void fastDFSDownload(String filePath) {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            // 链接FastDFS服务器，创建tracker和Stroage
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            int indexOf = filePath.indexOf("/");
            String group_name = filePath.substring(0, indexOf);
            String remote_filename = filePath.substring(indexOf+1);
            byte[] b = storageClient.download_file(group_name,remote_filename);
            if (b == null) {
                throw new IOException("文件" + filePath + "不存在");
            }
            String fileName = SerialNumber.Getnum() + remote_filename.substring(remote_filename.lastIndexOf("."));
            FileOutputStream fileOutputStream = new FileOutputStream(upload_location +"\\"+ fileName);
            IOUtils.write(b, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    @PostMapping("delete")
    public Msg delete(Long id, String fileId){
        try {
            // 初始化
            ClientGlobal.initByProperties("fastdfs-client.properties");
            // 创建tracker
            TrackerClient trackerClient = new TrackerClient();
            // 获取tracker服务
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            // 存储连接 storageClient
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            // 删除服务器上的文件
            int res = client.delete_file1(fileId);
            // 删除数据库记录
            boolean b = fileSystemService.removeById(id);
            // 关闭 trackerServer 连接
            trackerServer.close();
            if (b){
                return Msg.success();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Msg.fail();
    }

    /**
     * 获取所有文件
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    public Msg getAllList(){
        Page<FileListVO> page = new Page<>(1,10);
        System.out.println(page.toString());
        Page<FileListVO> list = fileSystemService.selectPageVo(page);
//        List<FileSystem> list = fileSystemService.list(new QueryWrapper<FileSystem>()
//                .isNotNull("id")
//                .orderByDesc("create_date"));
        return Msg.success().add("list",list);
    }


    /**
     * 文件大小换算处理函数
     * @param size
     * @return
     */
    public String setSize(Long size) {
        //获取到的size为：1705230
        var KB = 1024;
        var MB = KB * 1024;
        var GB = MB * 1024;
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B";
        }
        return resultSize;
    }
}
