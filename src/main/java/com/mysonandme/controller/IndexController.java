package com.mysonandme.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:首页控制层
 * Author:kbq
 * Date: 2019-11-15 17:54
 */
@RestController
@Api(tags = "首页信息")
@RequestMapping("/index")
public class IndexController {
}
