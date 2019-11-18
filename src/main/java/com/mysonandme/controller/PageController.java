package com.mysonandme.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:页面控制类
 * Author:kbq
 * Date: 2019-11-16 10:30
 */
@Controller
@Api(tags = "页面控制类")
@RequestMapping("/page/")
@Slf4j
public class PageController {


    @RequestMapping("login")
    public String login() {
        log.info("登录页面");
        return "login";
    }

    @RequestMapping("index")
    public String index() {
        log.info("首页");
        return "index";
    }

    @RequestMapping("error")
    public String error() {
        log.info("错误页");
        return "error";
    }
}
