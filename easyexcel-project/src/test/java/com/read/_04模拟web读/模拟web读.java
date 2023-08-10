package com.read._04模拟web读;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

@Slf4j
public class 模拟web读 {
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
    public void 读数据方式1() throws FileNotFoundException {
        //MultipartFile file,file.getInputStream()
        FileInputStream fis = new FileInputStream(filepath);

        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fis, ExcelDO.class, new PageReadListener<ExcelDO>(dataList -> {
            for (ExcelDO excelDO : dataList) {
                log.info("读取到一条数据{}", new Gson().toJson(excelDO));
            }
        })).sheet().doRead();
    }

}
