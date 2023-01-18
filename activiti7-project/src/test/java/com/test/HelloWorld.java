package com.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * @author eric
 * @date 2023/1/13 0:06
 **/
@Slf4j
public class HelloWorld {
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
    public void 获取操作数据库的服务对象(){
        log.info("{}",processEngine.getRepositoryService());
        log.info("{}",processEngine.getTaskService());
        log.info("{}",processEngine.getRuntimeService());
        log.info("{}",processEngine.getManagementService());
        log.info("{}",processEngine.getHistoryService());

        log.info("{}",processEngine.getDynamicBpmnService());
        log.info("{}",processEngine.getProcessEngineConfiguration());

    }


}
