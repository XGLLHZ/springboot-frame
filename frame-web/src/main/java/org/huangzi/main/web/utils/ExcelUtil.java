package org.huangzi.main.web.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.huangzi.main.web.entity.BlogEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/13 上午12:52
 * @description: excel 工具类
 */
public class ExcelUtil {

    /**
     * 导出
     * @param response
     * @param list 数据
     * @param fileTitle 文件名
     */
    public static void exportExcel(HttpServletResponse response, List<BlogEntity> list, String fileTitle) {
        try {

            //设置文件名
            String fileName = new String((fileTitle + ".xls").getBytes("utf-8"), "ISO-8859-1");

            //设置请求头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel;charset=gb2312");

            //导出
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), BlogEntity.class, list);
            workbook.write(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
