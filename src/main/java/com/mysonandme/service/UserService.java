package com.mysonandme.service;

import com.mysonandme.pojo.UserDO;

/**
 * Description:
 * Author:
 * Date: 2019-11-16 14:20
 */
public interface UserService {

    UserDO fetchUserDO(String userName);
}
