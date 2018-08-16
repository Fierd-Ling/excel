package com.hand.excel.service.impl;

import com.hand.excel.mapper.ExcelMapper;
import com.hand.excel.pojo.Excel;
import com.hand.excel.service.FileService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by zhongLingYun on 2018/8/15.
 * @author  zzz
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileService fileService;
    @Override
    public String insetToExcel(List<Excel> list) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
         //创建风格
        XSSFCellStyle style= workbook.createCellStyle();
        // 单元格
      style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
      style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
      style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
      // 字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("黑体");//字体
        font.setFontHeightInPoints((short)10);//设置字体
        style.setFont(font);


        // 创建页
        XSSFSheet sheet = workbook.createSheet();
        // 迭代写入数据
        for(int x =0;x<list.size();x++) {
            // 生成行
            XSSFRow row = sheet.createRow(x);
            // 设置行高
            row.setHeight((short)400);
            // 生成列
            XSSFCell cell = row.createCell(0);
            // 每行循环创建样式
            cell.setCellStyle(style);
            String str = list.get(x).getName();
            // 写入数据
            cell.setCellValue(str);
        }
        // 文件输出
        FileOutputStream fos = null;
        fos = new FileOutputStream(new File("C:\\Users\\zhongLingYun\\Desktop\\新建文件夹\\技术培训生签合同名单20180808.xlsx"));
        workbook.write(fos);
        return null;
    }


    @Autowired
    private ExcelMapper excelMapper;

    @Override
    public String readExcel(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return "multipartFile == null";
        }
        if (multipartFile.isEmpty()) {
            return "isEmpty";
        }
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        }
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "ok";
        }
        // 获取总页数
        int countSheet = workbook.getNumberOfSheets();
        List<Excel> list = new ArrayList<Excel>();
        for (int s = 0; s < countSheet; s++) {
            Sheet sheet = workbook.getSheetAt(s);
            // 获取总行数
            int countRows = sheet.getPhysicalNumberOfRows();
            for (int r = 0; r < countRows; r++) {
                Row row = sheet.getRow(r);
                // 去除第一行
                if (r == 0) {
                    continue;
                }
                // 一行是一个对象
                Excel excel = new Excel();
                // 获取列数
                int countCells = row.getPhysicalNumberOfCells();
                for (int c = 0; c < countCells; c++) {
                    switch (c) {
                        case 0:
                            String sid = (row.getCell(c)).toString();
                            String sd = sid.substring(sid.indexOf(""), sid.indexOf("."));
                            Integer id = Integer.parseInt(sd);
                            excel.setId(id);
                            break;
                        case 1:
                            String sNumber = (row.getCell(c)).toString();
                            String sn = sNumber.substring(sNumber.indexOf(""), sNumber.indexOf("."));
                            Integer number = Integer.parseInt(sn);
                            excel.setNumber(number);
                            break;
                        case 2:
                            String name = (row.getCell(c)).toString();
                            excel.setName(name);
                            break;
                        case 3:
                            String de = (row.getCell(c)).toString();
                            excel.setDepartment(de);
                            break;
                        case 4:
                            String email = (row.getCell(c)).toString();
                            excel.setEmail(email);
                            break;
                        case 5:
                            String ph = (row.getCell(c)).toString();
                            excel.setPhone(ph);
                            break;
                        case 6:
                            String teacher = (row.getCell(c)).toString();
                            excel.setTeacher(teacher);
                            break;
                        case 7:
                            String room = (row.getCell(c)).toString();
                            excel.setRoom(room);
                            break;
                        case 8:
                            String address = (row.getCell(c)).toString();
                            excel.setAddress(address);
                            break;
                        default:
                            break;
                    }
                }
                list.add(excel);

            }

        }
       // excelMapper.ins(list);
        try {
            fileService.insetToExcel(list);
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        }

        return "ok";
    }


}
