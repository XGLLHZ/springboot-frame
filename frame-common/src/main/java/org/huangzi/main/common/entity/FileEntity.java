package org.huangzi.main.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntityUtil;

/**
 * @author: XGLLHZ
 * @date: 2020/2/8 下午9:38
 * @description: 文件实体类
 */
@Data
@Accessors(chain = true)
public class FileEntity extends BaseEntityUtil {

    private Integer id;   //主键

    private String originalName;   //文件原名

    private String fileName;   //文件名

    private String fileType;   //文件类型

    private String fileSize;   //文件大小

    private String filePath;   //文件路径

}
