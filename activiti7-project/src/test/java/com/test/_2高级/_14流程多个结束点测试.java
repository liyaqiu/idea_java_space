package com.test._2高级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author eric
 * @date 2023/2/12 16:18
 **/
@Slf4j
public class _14流程多个结束点测试 {
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
                 .name("_14流程多个结束点测试")
                .addClasspathResource("bpmn9/demo9.bpmn")
                .addClasspathResource("bpmn9/demo9.png")
                .deploy();
        log.info("部署id{}",deployment.getId());
        log.info("部署name{}",deployment.getName());
    }



    @Test
    public void 启动流程(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        log.info("启动流程成功");
    }


    @Test
    public void 获取个人待执行任务方式1(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("wangwu")
                .list();
        for (Task task : taskList) {
            log.info("流程实例ID {}",task.getProcessInstanceId());
            log.info("任务ID {}",task.getId());
            log.info("任务负责人 {}",task.getAssignee());
            log.info("任务名称 {}",task.getName());
            //完成个人任务
            taskService.complete(task.getId());
        }
    }

    /*
        级联删除(强制)
        清理历史记录
    * */
    @Test
    public void 仓库_级联删除部署(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("37501",true);
    }
}
