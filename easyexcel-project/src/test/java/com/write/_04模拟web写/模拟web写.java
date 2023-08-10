package com.write._04模拟web写;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Date;
import java.util.List;

@Slf4j
public class 模拟web写 {
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
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    //@GetMapping("download")
    @Test
    public void 写数据方式2(/*HttpServletResponse response*/) throws FileNotFoundException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        //response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        //String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        //response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");



        // 写法2
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        //EasyExcel.write(response.getOutputStream(), ExcelDO.class).sheet("模板").doWrite(datas);
        EasyExcel.write(new FileOutputStream(filepath), ExcelDO.class).sheet("模板").doWrite(datas);
    }


}
