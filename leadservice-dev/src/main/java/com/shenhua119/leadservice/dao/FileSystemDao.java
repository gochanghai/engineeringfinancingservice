package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhua119.leadservice.entity.FileSystem;
import com.shenhua119.leadservice.vo.FileListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:09
 */
@Mapper
public interface FileSystemDao extends BaseMapper<FileSystem> {

    @Select("select * from file_system")
    Page<FileListVO> selectPageVo(Page<FileListVO> page);
}
