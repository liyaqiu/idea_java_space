package com.write._01写测试;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Slf4j
public class 写测试 {
    String filepath;
    List<ExcelDO> datas = ListUtils.newArrayList();

    @Before
    public void getFilePath() throws IOException {
        String rootPath = this.getClass().getClassLoader().getResource("").getPath();
        String fileName = "test_write.xlsx";
        File fullePath = new File(rootPath + fileName);

        try {
            if (!fullePath.exists()) {
                if (fullePath.createNewFile()) {
                    log.info("文件创建成功,{}", fullePath);
                } else {
                    log.info("文件创建失败,{}", fullePath);
                    throw new IOException();
                }
            }
        }catch (IOException e){
            throw e;
        }

        filepath = fullePath.getPath();
        log.info("路径是{}", filepath);
        initData();
    }


    private List<ExcelDO> initData() {
        for (int i = 0; i < 10; i++) {
            ExcelDO excelDO = new ExcelDO();
            excelDO.setString("eric" + i);
            excelDO.setDate(new Date());
            excelDO.setNumber(i);
            datas.add(excelDO);
        }
        return datas;
    }

    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void 写数据方式1() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(filepath, ExcelDO.class)
                .sheet("模板")
                .doWrite(() -> {
                    // 分页查询数据
                    return datas;
                });


    }

    /**
     *
     */
    @Test
    public void 写数据方式2() {
        // 写法2
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(filepath, ExcelDO.class).sheet("模板").doWrite(datas);

    }
    @Test
    public void 写数据方式3() {
        // 写法3
        // 这里 需要指定写用哪个class去写
        try (ExcelWriter excelWriter = EasyExcel.write(filepath, ExcelDO.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(datas, writeSheet);
        }
    }

}
