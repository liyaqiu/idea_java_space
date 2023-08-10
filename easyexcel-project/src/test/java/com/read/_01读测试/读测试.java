package com.read._01读测试;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
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


    /**
     * 最简单的读
     * 1. 创建excel对应的实体对象
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，
     * 3. 直接读即可
     */
    @Test
    public void 读数据方式1() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(filepath, ExcelDO.class, new PageReadListener<ExcelDO>(dataList -> {
            for (ExcelDO excelDO : dataList) {
                log.info("读取到一条数据{}", new Gson().toJson(excelDO));
            }
        })).sheet().doRead();
    }

    @Test
    public void 读数据方式2(){
        // 写法2：
        // 匿名内部类 不用额外写一个DemoDataListener
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filepath, ExcelDO.class, new ReadListener<ExcelDO>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100;
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
