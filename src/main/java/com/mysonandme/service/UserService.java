package com.mysonandme.service;

import com.mysonandme.pojo.sys.User;

/**
 * Description:用户业务层
 * Author:kbq
 * Date: 2019-11-04 13:54
 */
public interface UserService {


    public User getUserByuserName(String userName);
}
