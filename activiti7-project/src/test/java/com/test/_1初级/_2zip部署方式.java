package com.test._1初级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.junit.Before;
import org.junit.Test;

import java.util.zip.ZipInputStream;

/**
 * @author eric
 * @date 2023/1/28 16:09
 **/
@Slf4j
public class _2zip部署方式 {
    ProcessEngine processEngine;
    @Before
    public void 创建数据库表(){
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://192.168.88.10:3306/activitiproject?serverTimezone=UTC&nullCatalogMeansCurrent=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        //如果表不存在则新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        processEngine = configuration.buildProcessEngine();
        log.info("创建成功{}",processEngine);
    }

    @Test
    public void 发布流程(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ZipInputStream zipInputStream = new ZipInputStream(this.getClass().getClassLoader().getResourceAsStream("bpmn/demo1.zip"));
        Deployment deployment = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
        log.info("部署id:{}",deployment.getId());
        log.info("部署name:{}",deployment.getName());

    }
}
