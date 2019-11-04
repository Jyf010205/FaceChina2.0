package com.mysonandme.service.impl;

import com.mysonandme.mapper.TestMapper;
import com.mysonandme.pojo.Test;
import com.mysonandme.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> getTest() {
        return testMapper.getTest();
    }
}
