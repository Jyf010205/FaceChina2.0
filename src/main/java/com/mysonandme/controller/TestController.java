package com.mysonandme.controller;

import com.mysonandme.controller.base.BaseController;
import com.mysonandme.rep.ResponseMO;
import com.mysonandme.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jianyufeng
 * @description: 测试
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("index/")
public class TestController extends BaseController {
    @Autowired
    private TestService testService;

    @ApiOperation("获取测试用例")
    @GetMapping(value = "method")
    public ResponseMO getAllTest() {
        return success(testService.getTest());
    }
}
