package com.test._2高级;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author eric
 * @date 2023/2/8 23:49
 **/
public class _3Listener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        if ("经理审批".equals(delegateTask.getName()) && "create".equals(delegateTask.getEventName())) {
            delegateTask.setAssignee("zhangsan");
        }
    }
}
