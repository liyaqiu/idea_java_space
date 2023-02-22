package com.gzzn.text;

import com.gzzn.test.Launcher;
import com.gzzn.test.config.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author eric
 * @date 2023/2/15 23:29
 * Activiti7强耦合了String Security，可以考虑整合Activiti6
 **/
@SpringBootTest(classes = Launcher.class)
@Slf4j
public class HelloWorld_new {

    //新api
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private SecurityUtil securityUtil;

    //旧api
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void 查看流程定义() {
        //会将processes下的bpmn文件部署到数据库
        securityUtil.logInAs("zhangsan");
        Page<ProcessDefinition> processDefinitionPage =
                processRuntime.processDefinitions(Pageable.of(0, 10));
        System.out.println("可用的流程定义数量：" + processDefinitionPage.getTotalItems());
        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
            System.out.println("流程定义：" + pd);
        }
    }


    @Test
    public void 启动流程实例() {
        securityUtil.logInAs("system");
        ProcessInstance pi = processRuntime.start(ProcessPayloadBuilder.
                start().
                withProcessDefinitionKey("myProcess_1").
                build());
        System.out.println("流程实例ID：" + pi);
    }



    @Test
    public void 查询任务并完成自己的任务() {
        securityUtil.logInAs("lisi");
        Page<Task> taskPage=taskRuntime.tasks(Pageable.of(0,10));
        for (Task task:taskPage.getContent()){
            // 拾取任务
            taskRuntime.claim(TaskPayloadBuilder.
                    claim().
                    withTaskId(task.getId()).build());
            // 完成任务
            taskRuntime.complete(TaskPayloadBuilder.
                    complete().
                    withTaskId(task.getId()).build());
        }
    }

    /*
        级联删除(强制)
        清理历史记录
    * */
    @Test
    public void 仓库_级联删除部署(){
        repositoryService.deleteDeployment("38580c19-b006-11ed-90d1-107b44530a9d",true);
    }
}
