package com.hand.excel.mapper;

import com.hand.excel.pojo.Excel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongLingYun on 2018/8/16.
 */
@Repository
public interface ExcelMapper {


    void ins(List<Excel> list);
}
