package sp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sp.dao.CCMapper;
import sp.entity.CCEntity;

/**
 * @author lyq
 * @date 2022/1/6 11:43
 */
@RestController
@Slf4j
public class CCController {

    @Autowired
    CCMapper ccMapper;

    @Transactional
    @GetMapping("/cctest/{id}")
    public String cctest(@PathVariable String id){

        ccMapper.insert(new CCEntity(id, 100));

        log.debug("{}","cctest");
        return "cctest!!!";
    }



}
