package com.test._1初级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author eric
 * @date 2023/1/28 17:03
 **/
@Slf4j
public class _0API获取方式 {
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
        log.info("资源管理类{}",processEngine.getRepositoryService());
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment();
        repositoryService.createProcessDefinitionQuery();
        repositoryService.createModelQuery();

        log.info("任务管理类{}",processEngine.getTaskService());
        TaskService taskService = processEngine.getTaskService();
        taskService.createTaskQuery();
        //taskService.createAttachment()


        log.info("运行时管理类{}",processEngine.getRuntimeService());
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.createExecutionQuery();
        runtimeService.createProcessInstanceQuery();

        log.info("流程引擎管理类 {}",processEngine.getManagementService());
        ManagementService managementService = processEngine.getManagementService();
        managementService.createDeadLetterJobQuery();
        managementService.createJobQuery();
        managementService.createSuspendedJobQuery();
        managementService.createTablePageQuery();
        managementService.createTimerJobQuery();

        log.info("历史数据管理类{}",processEngine.getHistoryService());
        HistoryService historyService = processEngine.getHistoryService();
        historyService.createHistoricTaskInstanceQuery();
        historyService.createHistoricActivityInstanceQuery();
        historyService.createHistoricDetailQuery();
        historyService.createHistoricProcessInstanceQuery();
        historyService.createHistoricVariableInstanceQuery();
        historyService.createProcessInstanceHistoryLogQuery("");


        log.info("{}",processEngine.getDynamicBpmnService());
        log.info("{}",processEngine.getProcessEngineConfiguration());
    }


    @Test
    public void 仓库_查询流程定义(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.processDefinitionKey("myProcess_1").list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            log.info("getId->{}",processDefinition.getId());
            log.info("getName->{}",processDefinition.getName());
            log.info("getVersion->{}",processDefinition.getVersion());
            log.info("getDeploymentId->{}",processDefinition.getDeploymentId());
            log.info("------------------------------------------------------------");
        }
    }
}
