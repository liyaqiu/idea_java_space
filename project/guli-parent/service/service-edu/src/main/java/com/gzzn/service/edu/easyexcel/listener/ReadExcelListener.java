package com.gzzn.service.edu.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gzzn.service.edu.easyexcel.model.SubjectModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author eric
 * @date 2022/9/6 23:32
 **/
@Slf4j
public class ReadExcelListener extends AnalysisEventListener<SubjectModel> {

    private ReadExceCallback readExceCallback;

    public ReadExcelListener(ReadExceCallback readExceCallback) {
        this.readExceCallback = readExceCallback;
    }

    /*读取表头*/
    /*@Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.debug("invokeHeadMap {}",headMap);
    }*/

    /*读取每一行*/
    @Override
    public void invoke(SubjectModel subjectModel, AnalysisContext analysisContext) {
        log.debug("invoke {}",subjectModel);
        readExceCallback.readLine(subjectModel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("doAfterAllAnalysed");
    }

    @FunctionalInterface
    public interface ReadExceCallback{
        void readLine(SubjectModel subjectModel);
    }
}