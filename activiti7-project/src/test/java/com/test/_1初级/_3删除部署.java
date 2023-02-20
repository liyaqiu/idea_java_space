package com.test._1初级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
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
public class _3删除部署 {
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

    /*
        在没有任务运行的任务情况下可以删除
        不会清理历史记录
    * */
    @Test
    public void 仓库_删除部署(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("1");
    }

    /*
        级联删除(强制)
        清理历史记录
    * */
    @Test
    public void 仓库_级联删除部署(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("140001",true);
    }
}
