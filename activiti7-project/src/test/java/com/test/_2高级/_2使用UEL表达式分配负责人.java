package com.test._2高级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eric
 * @date 2023/2/8 23:19
 **/
@Slf4j
public class _2使用UEL表达式分配负责人 {

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
        log.info("创建工作流引擎{}",processEngine);
    }

    @Test
    public void 发布流程(){

        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                //.name("请假流程")
                .addClasspathResource("bpmn2/demo2.bpmn")
                .addClasspathResource("bpmn2/demo2.png")
                .deploy();
        log.info("部署id{}",deployment.getId());
        log.info("部署name{}",deployment.getName());
    }



    @Test
    public void 启动流程(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> variables = new HashMap<>();
        variables.put("user1", "zhangsan");
        variables.put("user2", "lisi");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_uel", variables);
        log.info("启动流程成功");
    }
}
