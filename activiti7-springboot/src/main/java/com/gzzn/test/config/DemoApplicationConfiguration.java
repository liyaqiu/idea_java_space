package com.gzzn.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Configuration
@Slf4j
public class DemoApplicationConfiguration {

    @Bean
    public UserDetailsService myUserDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        //使用二维数组添加用户，后面处理流程时用到的任务负责人，需要添加在这里
        String[][] usersGroupsAndRoles = {
                {"zhangsan", "123456", "ROLE_ACTIVITI_USER", "GROUP_dept1"},
                {"lisi", "123456", "ROLE_ACTIVITI_USER", "GROUP_dept1"},
                {"wangyu", "123456", "ROLE_ACTIVITI_USER", "GROUP_dept1"},
                {"other", "123456", "ROLE_ACTIVITI_USER", "GROUP_dept2"},
                {"system", "123456", "ROLE_ACTIVITI_USER"},
                {"admin", "123456", "ROLE_ACTIVITI_ADMIN"},
        };

        for (String[] user : usersGroupsAndRoles) {
            // 获取授权字符串数组
            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
            inMemoryUserDetailsManager.createUser(
                    new User(
                            user[0],
                            passwordEncoder().encode(user[1]),
                            authoritiesStrings.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList())));
        }
        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
