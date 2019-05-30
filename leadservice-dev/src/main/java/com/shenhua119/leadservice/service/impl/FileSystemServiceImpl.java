package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.FileSystemDao;
import com.shenhua119.leadservice.entity.FileSystem;
import com.shenhua119.leadservice.service.FileSystemService;
import com.shenhua119.leadservice.vo.FileListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Service
public class FileSystemServiceImpl extends ServiceImpl<FileSystemDao, FileSystem> implements FileSystemService {

    @Value("${fastdfs-path.upload_location}")
    private String upload_location;

    @Autowired
    private FileSystemDao fileSystemDao;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public FileSystem upload(MultipartFile file) {
        return null;
    }

    @Override
    public Page<FileListVO> selectPageVo(Page<FileListVO> page) {
        return (Page<FileListVO>) page.setRecords(this.baseMapper.selectPageVo(page));
    }
}
