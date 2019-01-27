package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.FireMaintenanceProjectDao;
import com.shenhua119.leadservice.entity.FireMaintenanceProjectEntity;
import com.shenhua119.leadservice.service.FireMaintenanceProjectService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Service
public class FireMaintenanceProjectServiceImpl extends ServiceImpl<FireMaintenanceProjectDao, FireMaintenanceProjectEntity> implements FireMaintenanceProjectService {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean importXlsOrXlsx(MultipartFile file) {

        try{
            InputStream inputStream = file.getInputStream();
            String originalFilename =  file.getOriginalFilename();
            String extension  = originalFilename.substring(originalFilename.lastIndexOf(".") +1);
            if(!inputStream.markSupported()) {
                inputStream = new PushbackInputStream(inputStream, 8);
            }
            if("xls".equals(extension)) {
                log.warn("HolidayController.importExecl Execl is 2003 and below");
                Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
                saveXlsOrXlsx(workbook);
                inputStream.close();
                return false;
            }else if("xlsx".equals(extension)){
                log.warn("HolidayController.importExecl Execl is version 2007 and above");
                Workbook workbook = new XSSFWorkbook(file.getInputStream());
                inputStream.close();
                return saveXlsOrXlsx(workbook);
            }else {
                log.error("HolidayController.importExecl Execl Format does not exist");
                return false;
            }
        }catch (Exception e){
            log.error("HolidayController.importExecl Execl data import Execption",e);
            return false;
        }

        //获取表数据

    }

    private boolean saveXlsOrXlsx(Workbook workbook){

        //获取Excel第一个sheet页
        DataFormatter formatter = new DataFormatter();
        List<FireMaintenanceProjectEntity> fireMaintenanceProjectList = new ArrayList<>();
        //定义错误信息记录集合
        List<String> list = new ArrayList<>();
        for(int numOfSheet = 0;numOfSheet < workbook.getNumberOfSheets(); numOfSheet++) {
            Sheet sheets = workbook.getSheetAt(numOfSheet);
            list.add("第Sheet" + numOfSheet + "页");
            //遍历行row
            for(int rowNum = 0; rowNum<=sheets.getLastRowNum();rowNum++) {
                Row row = sheets.getRow(rowNum);
                if (row.getRowNum() == 0) {
                    //如果是第一行直接跳出本次循环
                    continue;
                }

                FireMaintenanceProjectEntity fireMaintenanceProject = new FireMaintenanceProjectEntity();
                //将每一行数据的每一单元格数据取出
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateCellValue = dateFormat.format(row.getCell(0).getDateCellValue());
                    if(dateCellValue.isEmpty()){
                        log.error("第"+(rowNum+1)+"行，第1格节假日日期不能为空");
                    }else{
                        Date day = dateFormat.parse(dateCellValue);
                        fireMaintenanceProject.setCreateDate(day);
                    }
                } catch (Exception e) {
                    log.error("第"+(rowNum+1)+"行，第1格节假日日期格式错误");
                }
                String name = formatter.formatCellValue(row.getCell(1));
                if(name.isEmpty()){
                    log.error("第"+(rowNum+1)+"行，第2格项目名称不能为空");
                }else{
                    fireMaintenanceProject.setProjectName(name);
                }
                String floorArea = formatter.formatCellValue(row.getCell(2));
                if(floorArea.isEmpty()){
                    log.error("第"+(rowNum+1)+"行，第3格建筑面积不能为空");
                }else{
                    fireMaintenanceProject.setFloorArea(Double.parseDouble(floorArea));
                }
                String type = formatter.formatCellValue(row.getCell(3));
                if(type.isEmpty()){
                    log.error("第"+(rowNum+1)+"行，第4格建筑类型不能为空");
                }else{
                    fireMaintenanceProject.setProjectType(type);
                }
                String location = formatter.formatCellValue(row.getCell(4));
                if(location.isEmpty()){
                    log.error("第"+(rowNum+1)+"行，第5格项目地点不能为空");
                }else{
                    fireMaintenanceProject.setProjectLocation(location);
                }
                String remark = formatter.formatCellValue(row.getCell(5));
                fireMaintenanceProject.setRemark(remark).setCreateDate(new Date());

                fireMaintenanceProjectList.add(fireMaintenanceProject);
            }
        }
        System.out.println(fireMaintenanceProjectList.toString());
        //批量保存数据
        return saveBatch(fireMaintenanceProjectList);
//        return true;
    };
}
