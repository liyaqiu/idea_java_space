package com.gzzn.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gzzn.easyexcel.model.PersonModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author eric
 * @date 2022/9/6 23:32
 **/
@Slf4j
public class ExcelListener extends AnalysisEventListener<PersonModel> {

    /*读取表头*/
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.debug("invokeHeadMap {}",headMap);
    }

    /*读取每一行*/
    @Override
    public void invoke(PersonModel personModel, AnalysisContext analysisContext) {
        log.debug("invoke {}",personModel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("doAfterAllAnalysed");
    }
}
