package com.test;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Codegen {

    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://192.168.88.11:3306/test?characterEncoding=utf-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        //GlobalConfig globalConfig = createGlobalConfigUseStyle2();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        globalConfig.setAuthor("eric");
        globalConfig.setSourceDir("D:\\my_project\\idea_java_space\\mybitesflex\\src\\main\\java\\");

        //设置根包
        globalConfig.setBasePackage("org.test");

        //设置表前缀和只生成哪些表
        globalConfig.setTablePrefix("tb_");
        globalConfig.setGenerateTable("tb_person", "tb_account_session");


        //设置生成 controller
        globalConfig.setControllerGenerateEnable(true);

        //设置生成 entity
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);

        //设置生成servie
        globalConfig.setServiceGenerateEnable(true);

        //设置生成servieImpl
        globalConfig.setServiceImplGenerateEnable(true);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);


        //可以单独配置某个列
        ColumnConfig columnConfig = new ColumnConfig();
        columnConfig.setColumnName("tenant_id");
        columnConfig.setLarge(true);
        columnConfig.setVersion(true);
        globalConfig.setColumnConfig("tb_person", columnConfig);

        return globalConfig;
    }
}