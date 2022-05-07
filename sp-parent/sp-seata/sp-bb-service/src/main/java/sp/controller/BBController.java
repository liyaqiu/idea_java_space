package sp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sp.dao.BBMapper;
import sp.entity.BBEntity;

/**
 * @author lyq
 * @date 2022/1/6 11:43
 */
@RestController
@Slf4j
public class BBController {

    @Autowired
    BBMapper bbMapper;

    @GetMapping("/bbtest/{id}")
    public String bbtest(@PathVariable String id){

        bbMapper.insert(new BBEntity(id, 100));

        log.debug("{}","bbtest");
        return "bbtest!!!";
    }



}
