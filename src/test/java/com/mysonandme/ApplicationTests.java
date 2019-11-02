package com.mysonandme;

import com.mysonandme.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    TestMapper testMapper;

    @Test
    public void Test1() {
        com.mysonandme.pojo.Test test = new com.mysonandme.pojo.Test();
        test.setId("4").setTest("测试4");
        testMapper.insert(test);
        System.out.println("测试成功");
    }

    @Test
    public void Test2() {
        com.mysonandme.pojo.Test test = testMapper.selectById(4);
        System.out.println(test);
    }
}
