package com.mysonandme.service.impl;

import com.mysonandme.mapper.UserMapper;
import com.mysonandme.pojo.UserDO;
import com.mysonandme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:用户管理
 * Author:kbq
 * Date: 2019-11-16 14:21
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDO fetchUserDO(String userName) {
        return userMapper.fetchUserDO(userName);
    }
}
