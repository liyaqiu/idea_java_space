package com.test._1初级;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author eric
 * @date 2023/1/28 20:30
 **/

@Slf4j
public class _5历史记录 {
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
    public void 查询历史信息(){
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery().taskAssignee("zhangsan").list();
        for (HistoricTaskInstance taskInstance : taskInstanceList) {
            log.info("getName--{}",taskInstance.getName());
            log.info("getAssignee--{}",taskInstance.getAssignee());
            log.info("getStartTime--{}",taskInstance.getStartTime());

        }
    }

    @Test
    public void 查询单个流程定义的进度(){
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> taskInstanceList = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId("15001")
                .orderByHistoricTaskInstanceStartTime().asc()
                .list();
        for (HistoricTaskInstance taskInstance : taskInstanceList) {
            log.info("getName--{}",taskInstance.getName());
            log.info("getAssignee--{}",taskInstance.getAssignee());
            log.info("getStartTime--{}",taskInstance.getStartTime());

        }
    }


}
