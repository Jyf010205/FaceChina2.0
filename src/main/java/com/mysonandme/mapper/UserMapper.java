package com.mysonandme.mapper;

import com.mysonandme.pojo.UserDO;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * Author:
 * Date: 2019-11-16 14:19
 */
@Repository
public interface UserMapper {

    UserDO fetchUserDO(String userName);
}
