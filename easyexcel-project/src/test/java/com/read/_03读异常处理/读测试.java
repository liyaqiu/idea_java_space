package com.read._03读异常处理;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.List;

@Slf4j
public class 读测试 {
    String filepath;

    @Before
    public void getFilePath() {
        URL url = this.getClass().getClassLoader().getResource("test.xlsx");
        filepath = url.getFile();
        log.info("路径是{}", filepath);
    }

    @Test
    public void 读数据方式2() {
        // 写法2：
        // 匿名内部类 不用额外写一个DemoDataListener
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filepath, ExcelDO.class, new ReadListener<ExcelDO>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100;

            /**
             * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
             *
             * @param exception
             * @param context
             * @throws Exception
             */
            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception{
                log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
                // 如果是某一个单元格的转换异常 能获取到具体行号
                // 如果要获取头的信息 配合invokeHeadMap使用
                if (exception instanceof ExcelDataConvertException) {
                    ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
                    log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                            excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData().getStringValue());
                } else {
                    //其他未知异常则停止解析
                    throw exception;
                }
            }

            /**
             *临时存储
             */
            private List<ExcelDO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(ExcelDO data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
                log.info("存储数据库成功！");
            }
        }).sheet().doRead();
    }


}
