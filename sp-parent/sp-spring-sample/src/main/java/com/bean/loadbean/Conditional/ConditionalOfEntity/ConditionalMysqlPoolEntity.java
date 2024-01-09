package com.bean.loadbean.Conditional.ConditionalOfEntity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/5/11 15:54
 **/
@Component
@ConditionalOnBean(type = "com.bean.loadbean.Conditional.ConditionalOfEntity.ConditionalMysqlEntity")
public class ConditionalMysqlPoolEntity {
}
