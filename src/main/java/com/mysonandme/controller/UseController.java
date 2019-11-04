package com.mysonandme.controller;

import com.mysonandme.controller.base.BaseController;
import com.mysonandme.pojo.sys.User;
import com.mysonandme.rep.ResponseMO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:  登录接口
 * Author:kbq
 * Date: 2019-11-04 13:47
 */

@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/user/")
public class UseController extends BaseController {


    @PostMapping("login")
    @ApiOperation("用户登录")
    public ResponseMO userLogin(User user) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try{
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermission("add");
        }catch (AuthenticationException e) {
            e.printStackTrace();
        }catch (AuthorizationException e) {
            e.printStackTrace();
        }

        return success("Login successfully");
    }


    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @GetMapping("index")
    public String index() {
        return "index";
    }


}
