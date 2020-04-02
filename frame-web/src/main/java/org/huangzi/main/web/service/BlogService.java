package org.huangzi.main.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.BlogEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: XGLLHZ
 * @date: 2020/3/9 下午7:55
 * @description: 博客事务层接口
 */
public interface BlogService extends IService<BlogEntity> {

    /**
     * 列表
     * @param blogEntity
     * @return
     */
    APIResponse getList(BlogEntity blogEntity);

    /**
     * 详情
     * @param blogEntity
     * @return
     */
    APIResponse getBlog(BlogEntity blogEntity);

    /**
     * 新增
     * @param blogEntity
     * @return
     */
    APIResponse insertBlog(BlogEntity blogEntity);

    /**
     * 删除
     * @param blogEntity
     * @return
     */
    APIResponse deleteBlog(BlogEntity blogEntity);

    /**
     * 修改
     * @param blogEntity
     * @return
     */
    APIResponse updateBlog(BlogEntity blogEntity);

    /**
     * 导出
     * @param response
     * @param blogEntity
     * @return
     */
    APIResponse exportBlog(HttpServletResponse response, BlogEntity blogEntity);

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    APIResponse uploadImage(MultipartFile multipartFile);

}
