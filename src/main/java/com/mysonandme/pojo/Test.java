package com.mysonandme.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: jianyufeng
 * @description: 测试
 */
@TableName("Test")
@Data
@Accessors(chain = true)
public class Test {
    @TableId
    private String id;

    private String Test;
}
