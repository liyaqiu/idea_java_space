package com.test;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        configuration.setJdbcUrl("jdbc:mysql://192.168.88.10:3306/flowableproject?serverTimezone=UTC&nullCatalogMeansCurrent=true");
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
        Deployment deployment = repositoryService.createDeployment()
                //.name("请假流程")
                .addClasspathResource("bpmn/demo1.bpmn")
                .addClasspathResource("bpmn/demo1.png")
                .deploy();
        log.info("部署id{}",deployment.getId());
        log.info("部署name{}",deployment.getName());
    }

    @Test
    public void 查询流程定义(){
        RepositoryService repositoryService = processEngine.getRepositoryService();

        ProcessDefinitionQuery processDefinition = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = processDefinition.listPage(0, 100);
        for (ProcessDefinition definition : processDefinitions) {
            log.info("流程定义:id:{}，name:{}，key:{}",definition.getId(),definition.getName(),definition.getKey());
        }
    }

    @Test
    public void 启动流程(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //ProcessInstance processInstance = runtimeService.startProcessInstanceById("myProcess_1:1:4");
        //当有key重复的时候使用最新版本的key
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");

        log.info("流程定义ID {}",processInstance.getProcessDefinitionId());
        log.info("流程实例ID {}",processInstance.getId());
        log.info("流程实例ID {}",processInstance.getProcessInstanceId());
        log.info("当前活动ID {}",processInstance.getActivityId());
    }

    @Test
    public void 删除流程实例(){
        processEngine.getRuntimeService().deleteProcessInstance("2501", "终止操作了");
        log.info("{}");
    }

    /*
        级联删除(强制)
        清理历史记录
    * */
    @Test
    public void 仓库_级联删除部署(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("7501",true);
    }
}
