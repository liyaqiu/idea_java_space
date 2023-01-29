package com.test._1初级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author eric
 * @date 2023/1/28 18:08
 **/
@Slf4j
public class _4下载文件 {

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
    public void 查询所有流程定义信息(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitions = processDefinitionQuery.list();
        for (ProcessDefinition processDefinition : definitions) {
            log.info("getId->{}",processDefinition.getId());
            log.info("getName->{}",processDefinition.getName());
            log.info("getVersion->{}",processDefinition.getVersion());
            log.info("getDeploymentId->{}",processDefinition.getDeploymentId());
            log.info("------------------------------------------------------------");
        }
    }

    @Test
    public void 下载文件() throws IOException {
        //查询单个流程定义信息
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId("myProcess_1:1:42504").singleResult();
        log.info("getId->{}", processDefinition.getId());
        log.info("getName->{}", processDefinition.getName());
        log.info("getVersion->{}", processDefinition.getVersion());
        log.info("getDeploymentId->{}", processDefinition.getDeploymentId());

        //方式1
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        OutputStream outputStream = new FileOutputStream("E:\\demo.png");
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();

        //方式2
        /*List<String> resourceNames = repositoryService.getDeploymentResourceNames(processDefinition.getDeploymentId());
        for (String resourceName : resourceNames) {
            InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
            System.out.println(resourceAsStream);
        }*/
    }
}
