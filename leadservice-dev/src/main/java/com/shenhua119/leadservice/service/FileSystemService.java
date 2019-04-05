package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.FileSystem;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
public interface FileSystemService extends IService<FileSystem> {
    /**
     * 文件上传
     * @param file
     * @return
     */
    FileSystem upload(MultipartFile file);
}
