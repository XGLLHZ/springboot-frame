package org.huangzi.main.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2019/11/8 10:22
 * @description: 测试实体类
 */
@Data
@Accessors(chain = true)
public class TestEntity implements Serializable {

    private Integer id;

    private String testName;

    private String testMessage;

}
