package sp.tcc.controller;

import feign.client.BBServiceClient;
import feign.client.CCServiceClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sp.tcc.action.TCCAction;

/**
 * @author lyq
 * @date 2022/1/6 11:43
 */
@RestController
@Slf4j
public class TccController {



    @Autowired
    TCCAction tccAction;
    @Autowired
    BBServiceClient bbServiceClient;
    @Autowired
    CCServiceClient ccServiceClient;

    @GlobalTransactional
    @GetMapping("/tcctest/{uid}/{money}/{bl}")
    public String aatest(@PathVariable String uid,@PathVariable int money,@PathVariable String bl){
        log.debug("{}","tcctest");

        tccAction.tryTest(uid,money);

        bbServiceClient.bbtest(uid);
        ccServiceClient.cctest(uid);

        if("false".equals(bl)){
            throw new RuntimeException("人工异常...");
        }

        return "aatest!!!";
    }



}
