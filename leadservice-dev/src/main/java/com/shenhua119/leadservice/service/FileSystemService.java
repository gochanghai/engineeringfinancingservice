package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.FileSystem;
import com.shenhua119.leadservice.vo.FileListVO;
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

    Page<FileListVO> selectPageVo(Page<FileListVO> page);
}
