package org.huangzi.main.wx.mp.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/6/18 下午10:25
 * @description:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MpVo implements Serializable {

    private String redirectUrl;

    private String code;

}
