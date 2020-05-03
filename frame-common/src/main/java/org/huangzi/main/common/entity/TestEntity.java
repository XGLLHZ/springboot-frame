package org.huangzi.main.common.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2019/11/8 10:22
 * @description: 测试实体类
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TestEntity implements Serializable {

    private Integer id;

    private String testName;

    private String testMessage;

}
