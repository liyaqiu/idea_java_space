package com.test;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.idm.api.UserQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * @author eric
 * @date 2023/1/13 0:06
 **/
@Slf4j
public class _02_添加用户和组 {

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
    public void 添加用户(){
        IdentityService identityService = processEngine.getIdentityService();
        User user = identityService.newUser(UUID.randomUUID().toString().replaceAll("-",""));
        user.setFirstName("li");
        user.setLastName("eric");
        user.setPassword("123456");
        identityService.saveUser(user);

        //identityService.deleteUser("用户1");
    }


    @Test
    public void 添加组(){
        IdentityService identityService = processEngine.getIdentityService();
        Group group = identityService.newGroup(UUID.randomUUID().toString().replaceAll("-", ""));
        group.setName("组1");
        group.setType("1");
        identityService.saveGroup(group);
    }

    @Test
    public void 用户和组绑定(){
        IdentityService identityService = processEngine.getIdentityService();
        UserQuery userQuery = identityService.createUserQuery();
        List<User> users = userQuery.list();
        for (User user : users) {
            identityService.createMembership(user.getId(),"83913dceb96544cc948bf29a2556ace3");
        }
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
