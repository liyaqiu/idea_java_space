package com.write._02指定写入列;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
public class 指定写入列 {
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
        } catch (IOException e) {
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
     *
     */
    @Test
    public void 写数据方式2() {
        // 写法2
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(filepath, ExcelDO.class).sheet("模板").doWrite(datas);

    }


}
