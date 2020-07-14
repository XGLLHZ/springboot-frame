package org.huangzi.main.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.dto.FileDto;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.common.utils.FileUtil;
import org.huangzi.main.web.mapper.ContentMapper;
import org.huangzi.main.web.entity.BlogEntity;
import org.huangzi.main.web.service.BlogService;
import org.huangzi.main.web.entity.ContentEntity;
import org.huangzi.main.web.mapper.BlogMapper;
import org.huangzi.main.web.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/3/9 下午7:56
 * @description: 博客事务层实现类
 */
@Primary
@Service("BlogServiceImpl")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, BlogEntity> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public APIResponse getList(BlogEntity blogEntity) {
        if (blogEntity.getSearchTime() != null && blogEntity.getSearchTime().length > 0) {
            blogEntity.setStartTime(blogEntity.getSearchTime()[0]);
            blogEntity.setEndTime(blogEntity.getSearchTime()[1]);
        }
        Page<BlogEntity> page = new Page<>(blogEntity.getCurrentPage(), blogEntity.getPageSize());
        List<BlogEntity> list = blogMapper.getList(page, blogEntity);
        Integer total = blogMapper.getTotal(blogEntity);
        Map<String, Object> map = new HashMap<>(2);
        if (list == null || total == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        map.put(ConstConfig.DATA_LIST, list);
        map.put(ConstConfig.TOTAL, total);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getBlog(BlogEntity blogEntity) {
        BlogEntity blogEntity1 = blogMapper.selectById(blogEntity.getId());
        ContentEntity contentEntity = contentMapper.selectOne(new QueryWrapper<ContentEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("content_type", ConstConfig.TYPE_ONE)
                .eq("object_id", blogEntity.getId()));
        if (blogEntity1 == null || contentEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        if (contentEntity.getId() != null) {
            blogEntity1.setContentId(contentEntity.getId());
        }
        if (contentEntity.getContent() != null) {
            blogEntity1.setContent(contentEntity.getContent());
        }
        blogEntity1.setReadNumber(blogEntity1.getReadNumber() + ConstConfig.TYPE_ONE);
        int res = blogMapper.updateById(blogEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, blogEntity1);
        return new APIResponse(map);
    }

    @Override
    @Transactional
    public APIResponse insertBlog(BlogEntity blogEntity) {
        blogEntity.setBlogAuthor("XGLLHZ");
        int res = blogMapper.insert(blogEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setContentType(ConstConfig.TYPE_ONE);
        contentEntity.setObjectId(blogEntity.getId());
        contentEntity.setContent(blogEntity.getContent());
        res = contentMapper.insert(contentEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse deleteBlog(BlogEntity blogEntity) {
        BlogEntity blogEntity1 = blogMapper.selectById(blogEntity.getId());
        ContentEntity contentEntity = contentMapper.selectOne(new QueryWrapper<ContentEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("content_type", ConstConfig.TYPE_ONE)
                .eq("object_id", blogEntity.getId()));
        if (blogEntity1 == null || contentEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        blogEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res =  blogMapper.updateById(blogEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        contentEntity.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        res = contentMapper.updateById(contentEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse updateBlog(BlogEntity blogEntity) {
        BlogEntity blogEntity1 = blogMapper.selectById(blogEntity.getId());
        ContentEntity contentEntity = contentMapper.selectOne(new QueryWrapper<ContentEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("content_type", ConstConfig.TYPE_ONE)
                .eq("object_id", blogEntity.getId()));
        if (blogEntity1 == null || contentEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = blogMapper.updateById(blogEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        contentEntity.setContent(blogEntity.getContent());
        res = contentMapper.updateById(contentEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse exportBlog(HttpServletResponse response, BlogEntity blogEntity) {
        List<BlogEntity> list = blogMapper.selectList(new QueryWrapper<BlogEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .orderByAsc("create_time"));
        if (list == null ) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        ExcelUtils.exportExcel(response, list, blogEntity.getFileTitle());
        return new APIResponse();
    }

    @Override
    public APIResponse uploadImage(MultipartFile multipartFile) {
        FileDto fileEntity = FileUtil.uploadFile(multipartFile);
        if (fileEntity == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (fileEntity.getFilePath() == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, fileEntity.getFilePath());
        return new APIResponse(map);
    }

    @Override
    public APIResponse getAllBlog(BlogEntity blogEntity) {
        List<BlogEntity> list;
        if (blogEntity.getBlogType() == null || blogEntity.getBlogType() == 0) {
            list = blogMapper.selectList(new QueryWrapper<BlogEntity>()
                    .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                    .orderByDesc("update_time"));
        } else {
            list = blogMapper.selectList(new QueryWrapper<BlogEntity>()
                    .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                    .eq("blog_type", blogEntity.getBlogType())
                    .orderByDesc("update_time"));
        }
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_LIST, list);
        return new APIResponse(map);
    }

}
