package com.mysonandme.controller;

import com.mysonandme.controller.base.BaseController;
import com.mysonandme.pojo.UserDO;
import com.mysonandme.rep.ResponseMO;
import com.mysonandme.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:
 * Date: 2019-11-16 14:23
 */
@RequestMapping("/userManage/")
@RestController
@Api(tags = "用户管理")
@Slf4j
public class UserController extends BaseController {


    @Autowired
    private UserService userService;


    @PostMapping("login")
    @ApiOperation(value = "登录")
    public ResponseMO login(@RequestParam("userName") String userName) {
        UserDO userDO = userService.fetchUserDO(userName);
        return success(userDO);
    }

}
