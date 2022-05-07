package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
