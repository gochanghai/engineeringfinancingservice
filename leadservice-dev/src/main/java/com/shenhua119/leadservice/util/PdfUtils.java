package com.shenhua119.leadservice.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author liuchanghai
 * @create 2019-01-09 10:47
 */
public class PdfUtils {
    /**
     * EXCEL 转  PDF     *
     * @param file
     */
    public void excelOfPdf(MultipartFile file) {
        boolean result = false;
        try {
            InputStream license = file.getInputStream();
            result = true;
            if (result == true) {
                Workbook wb = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));//原始excel路径
//                String fles = filepath.substring(0, filepath.lastIndexOf("."));
//                File file = new File(".pdf");// 输出路径
//                FileOutputStream fileOS = new FileOutputStream(file);
//                wb.save(fileOS, SaveFormat.PDF);
//                fileOS.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
