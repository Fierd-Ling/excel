package com.hand.excel.controlle;

import com.hand.excel.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by zhongLingYun on 2018/8/15.
 */
@RequestMapping(value="/excel")
@Controller
public class ExcelControll {

    @Autowired
    private FileService fileService;

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public @ResponseBody String insert( MultipartFile inputfil){
        Long begin=System.currentTimeMillis();
       // System.out.print(123);
       if (inputfil==null){
            return  "null";
        }
        if(inputfil.isEmpty()){
            return "isEmpty";
        }
        String  result= null;

            result = fileService.readExcel(inputfil);
            // 计算时间
            Long end =System.currentTimeMillis();
            System.out.println(end-begin);
        return result;
    }

}
