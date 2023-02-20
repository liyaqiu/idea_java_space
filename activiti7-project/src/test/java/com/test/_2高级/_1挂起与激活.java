package com.test._2高级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author eric
 * @date 2023/1/28 17:03
 **/
@Slf4j
public class _1挂起与激活 {
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
    public void 启动流程(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1", "10002");
        log.info("getBusinessKey:{}",processInstance.getBusinessKey());
    }

    /*此流程定义不允许在生成新的流程实例，并且该流程定义下的所有流程实例都不能操作*/
    @Test
    public void 通过流程定义来挂起与激活_方式1(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.processDefinitionKey("myProcess_1").list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            log.info("getId:{} isSuspended:{}",processDefinition.getId(),processDefinition.isSuspended());
            if(processDefinition.isSuspended()){
                //挂起
                repositoryService.activateProcessDefinitionById(processDefinition.getId(), true, null);
            }else{
                //激活
                repositoryService.suspendProcessDefinitionById(processDefinition.getId(), true, null);
            }
        }
    }

    /*此流程定义不允许在生成新的流程实例，并且该流程定义下的所有流程实例都不能操作*/
    @Test
    public void 通过流程定义来挂起与激活_方式2(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId("myProcess_1:1:42504").singleResult();
        log.info("getId:{} isSuspended:{}",processDefinition.getId(),processDefinition.isSuspended());
        if(processDefinition.isSuspended()){
            //挂起
            repositoryService.activateProcessDefinitionById(processDefinition.getId(), true, null);
        }else{
            //激活
            repositoryService.suspendProcessDefinitionById(processDefinition.getId(), true, null);
        }
    }

    /*此流程实例不能操作*/
    @Test
    public void 通过流程实例挂起与激活(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId("55001").singleResult();

        log.info("getId:{} isSuspended:{}",processInstance.getId(),processInstance.isSuspended());
        if(processInstance.isSuspended()){
            //激活
            runtimeService.activateProcessInstanceById(processInstance.getId());
        }else{
            //挂起
            runtimeService.suspendProcessInstanceById(processInstance.getId());
        }

    }

}
