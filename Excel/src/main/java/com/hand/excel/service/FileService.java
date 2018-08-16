package com.hand.excel.service;

import com.hand.excel.pojo.Excel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhongLingYun on 2018/8/15.
 */
@Service
public interface FileService {

    String readExcel(MultipartFile multipartFile);


    String insetToExcel(List<Excel> list) throws IOException;
}
