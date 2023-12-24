package com.xxljob.task._3任务分片;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpiltTask {

    @Value("${server.port}")
    int serverPort;

    /*
        需要配置路由策略-分片广播
    * */
    @XxlJob("spilt_task")
    public ReturnT<String> spilt_task(String param) throws Exception {

        int shardIndex = ShardingUtil.getShardingVo().getIndex();
        int shardTotal = ShardingUtil.getShardingVo().getTotal();

        if(shardTotal==1){
            //dao.selectAll()
        }else{
            //dao.selectMod()
            //select * from user u where mod(u.id,${shardTotal}) = ${shardIndex}
        }

        XxlJobLogger.log("分片总数:{} 分片索引:{} 端口:{} 参数:{}",shardTotal,shardIndex,serverPort,param);
        log.info("分片总数:{} 分片索引:{} 端口:{} 参数:{}",shardTotal,shardIndex,serverPort,param);

        return ReturnT.SUCCESS;
    }
}
