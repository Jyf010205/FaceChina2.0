package com.mysonandme.controller;

import com.mysonandme.pojo.Test;
import com.mysonandme.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: jianyufeng
 * @description: 测试
 */
@Api(tags = "测试接口")
@RestController
public class TestController {
    @Autowired
    TestService testService;

    @ApiOperation("测试接口")
    @GetMapping(value = "test")
    public List<Test> Test(){
        return testService.Test();
    }
}
