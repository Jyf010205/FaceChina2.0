package com.mysonandme.service;

import com.github.pagehelper.PageHelper;
import com.mysonandme.mapper.TestMapper;
import com.mysonandme.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jianyufeng
 * @description: 测试
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public List<Test> Test() {
        PageHelper.startPage(1,2);
        return testMapper.Test();
    }
}
