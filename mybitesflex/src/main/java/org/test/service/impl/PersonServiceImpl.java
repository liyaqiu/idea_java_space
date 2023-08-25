package org.test.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.test.entity.Person;
import org.test.mapper.PersonMapper;
import org.test.service.PersonService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author eric
 * @since 2023-08-22
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
