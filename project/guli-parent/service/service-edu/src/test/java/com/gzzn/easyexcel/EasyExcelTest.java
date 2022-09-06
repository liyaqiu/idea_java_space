package com.gzzn.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.gzzn.easyexcel.listener.ExcelListener;
import com.gzzn.easyexcel.model.PersonModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/6 21:13
 **/
@Slf4j
public class EasyExcelTest {
    @Test
    public void testWrite(){
        log.debug("测试写入");
        String fileName = "C:\\Users\\admin\\Desktop\\新建文件夹\\demo3.xlsx";

        List<PersonModel> personList = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            personList.add(new PersonModel("名字"+i,1+i));
        }

        EasyExcel.write(fileName, PersonModel.class).sheet("个人信息").doWrite(personList);
    }
    @Test
    public void testRead(){
        log.debug("测试读取");
        String fileName = "C:\\Users\\admin\\Desktop\\新建文件夹\\demo3.xlsx";

        EasyExcel.read(fileName, PersonModel.class,new ExcelListener()).sheet("个人信息").doRead();
    }
}
