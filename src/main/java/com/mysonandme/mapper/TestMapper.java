package com.mysonandme.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysonandme.pojo.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: jianyufeng
 * @description: 测试
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {
    List<Test> getTest();
}
