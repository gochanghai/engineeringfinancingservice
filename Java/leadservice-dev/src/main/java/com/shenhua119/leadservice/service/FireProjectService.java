package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.FireProjectEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
public interface FireProjectService extends IService<FireProjectEntity> {

    boolean importXlsOrXlsx(MultipartFile file);
}
