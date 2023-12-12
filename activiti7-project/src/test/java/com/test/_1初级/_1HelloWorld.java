package com.test._1初级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author eric
 * @date 2023/1/13 0:06
 **/
@Slf4j
public class _1HelloWorld {
    ProcessEngine processEngine;
    @Before
    public void 创建数据库表(){
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://192.168.88.10:3306/activitiproject?serverTimezone=UTC&nullCatalogMeansCurrent=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        //false: 默认值，activiti在启动时，会对比数据表中保存的版本，如果没有表或者版本不匹配抛出异常。(生产环境常用)
        //true: activiti会对数据中所有表进行更新操作，如果表不存在，则自动创建(开发常用)
        //create_drop: 在activiti启动时创建表，在关闭时伤处表(必须手动关闭殷勤，才能删除表)(单元测试常用)
        //drop_create: 在activiti启动时删除原来的旧表，然后在创建新表(不需要手动关闭引擎)
        //如果表不存在则新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        processEngine = configuration.buildProcessEngine();
        log.info("创建成功{}",processEngine);
    }

    /*
    ACT_RE_DEPLOYMENT 流程部署表，每次部署都会增加一条记录
    ACT_RE_PROCDEF 流程定义表
    ACT_GE_BYTEARRAY 流程资源表
    ACT_GE_PROPERTY 更新
    */
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

    /**
     * ACT_HI_ACTINST 流程实例执行理事
     * ACT_HI_IDENTITYLINK 流程的参与用户信息历史
     * ACT_HI_PROCINST 流程历史历史信息
     * ACT_HI_TASKINST 流程任务历史信息
     * ACT_RU_EXECUTION 流程正在执行信息
     * ACT_RU_IDENTITYLINK 流程的参与用户信息
     * ACT_RU_TASK 任务信息
     * */
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
        SELECT
            ACT_HI_TASKINST
            ACT_HI_ACTINST
            ACT_HI_IDENTITYLINK
            ACT_RU_TASK
            ACT_RU_IDENTITYLINK
        UPDATE
            ACT_HI_TASKINST
            ACT_HI_ACTINST
            ACT_RU_EXECUTION
        DELETE
            ACT_RU_TASK
    */
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

    @Test
    public void 获取个人待执行任务方式2(){
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("zhangsan")
                .singleResult();
        log.info("流程实例ID {}",task.getProcessInstanceId());
        log.info("任务ID {}",task.getId());
        log.info("任务负责人 {}",task.getAssignee());
        log.info("任务名称 {}",task.getName());
        //完成个人任务
        taskService.complete(task.getId());

    }

    /*
        级联删除(强制)
        清理历史记录
    * */
    @Test
    public void 仓库_级联删除部署(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("5001",true);
    }

}
