package com.example.shirodemo.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * 使用aliba的easyExcel读取excel文件
 *
 * @author jocken
 * @date 2021/3/16
 */
public class ExcelListener extends AnalysisEventListener<Object> {

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        System.out.println("--" + o.toString());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
