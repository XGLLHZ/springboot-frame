package org.huangzi.main.common.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.huangzi.main.common.utils.BaseEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/2/8 下午9:38
 * @description: 文件实体类
 */
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FileDto extends BaseEntity {

    private String originalName;   //文件原名

    private String fileName;   //文件名

    private String fileType;   //文件类型

    private String fileSize;   //文件大小

    private String filePath;   //文件路径

}
