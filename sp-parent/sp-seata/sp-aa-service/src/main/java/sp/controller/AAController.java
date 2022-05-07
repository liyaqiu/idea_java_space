package sp.controller;

import feign.client.BBServiceClient;
import feign.client.CCServiceClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sp.dao.AAMapper;
import sp.entity.AAEntity;

/**
 * @author lyq
 * @date 2022/1/6 11:43
 */
@RestController
@Slf4j
public class AAController {

    @Autowired
    BBServiceClient bbServiceClient;
    @Autowired
    CCServiceClient ccServiceClient;

    @Autowired
    AAMapper aaMapper;

    @GlobalTransactional
    @GetMapping("/aatest/{id}/{bl}")
    public String aatest(@PathVariable String id,@PathVariable String bl){
        log.debug("{}","aatest");

        aaMapper.insert(new AAEntity(id, 100));


        bbServiceClient.bbtest(id);
        ccServiceClient.cctest(id);

        if("false".equals(bl)){
            throw new RuntimeException("人工异常...");
        }

        return "aatest!!!";
    }



}
