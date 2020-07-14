package org.huangzi.main.common.dto;

import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/6/17 下午3:45
 * @description:
 */
@Data
@ToString
@NoArgsConstructor
public class TokenDto implements Serializable {

    private Integer userId;

    private String userName;

    private String account;

    private Integer userType;

    private String token;

}
