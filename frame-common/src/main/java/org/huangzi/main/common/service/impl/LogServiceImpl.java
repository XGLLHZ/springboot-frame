package org.huangzi.main.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.dto.LogExportDto;
import org.huangzi.main.common.dto.LogImportDto;
import org.huangzi.main.common.utils.*;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.dto.OnlineUserDto;
import org.huangzi.main.common.service.OnlineUserService;
import org.huangzi.main.common.entity.LogEntity;
import org.huangzi.main.common.mapper.LogMapper;
import org.huangzi.main.common.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午7:17
 * @description: 日志事务层实现类
 */
@Service
@Primary
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {

    @Autowired
    LogMapper logMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    OnlineUserService onlineUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertLog(ProceedingJoinPoint joinPoint, LogEntity logEntity) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        HttpServletRequest request = HttpUtil.getRequest();

        //操作
        if (logEntity != null) {
            logEntity.setOperateName(logAnnotation.value());
        }

        assert logEntity != null;

        //用户名
        String ip = StringUtil.getUserIp(request);
        List<String> list = redisUtil.getListKey(ConstConfig.ONLINE_KEY + "*");
        Collections.reverse(list);
        List<OnlineUserDto> list1 = new ArrayList<>();
        OnlineUserDto onlineUserEntity1 = null;
        for (String key : list) {
            onlineUserEntity1 = (OnlineUserDto) redisUtil.getValue(key);
            if (onlineUserEntity1 != null) {
                if (onlineUserEntity1.toString().contains(ip)) {
                    list1.add(onlineUserEntity1);
                }
            }
        }
        if (list1.size() > 0) {
            logEntity.setUserName(list1.get(0).getUserName());
        }
        logEntity.setUserName("本地开发者");

        //用户 ip
        logEntity.setRequestIp(ip);

        //类名 + 方法名
        String className = joinPoint.getTarget().getClass().getName() + "." + methodSignature.getName() + "()";
        logEntity.setClassName(className);

        //参数
        StringBuilder stringBuilder = new StringBuilder("{");
        Object[] objects = joinPoint.getArgs();
        String[] strings = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (objects != null) {
            for (int i = 0; i < strings.length; i++) {
                stringBuilder.append(" ").append(strings[i]).append(": ").append(objects[i]);
            }
        }
        logEntity.setRequestParams(stringBuilder + "}");

        //请求 api
        String url = request.getRequestURI();
        logEntity.setRequestApi(url);

        //用户地址
        logEntity.setAddress(StringUtil.getAddressByIp(ip));

        //浏览器
        logEntity.setBrowser(StringUtil.getUserBrowser(request));

        int res = logMapper.insert(logEntity);
        return res;
    }

    @Override
    public APIResponse getList(LogEntity logEntity) {
        if (logEntity.getSearchTime().length > 0) {
            logEntity.setStartTime(logEntity.getSearchTime()[0]);
            logEntity.setEndTime(logEntity.getSearchTime()[1]);
        }
        Page<LogEntity> page = new Page<>(logEntity.getCurrentPage(), logEntity.getPageSize());
        List<LogEntity> list = logMapper.list(page, logEntity);
        int total = logMapper.total(logEntity);
        Map<String, Object> map = new HashMap<>();
        if (list == null) {
            map.put("dataList", new ArrayList<>());
            map.put("total", 0);
        }
        map.put("dataList", list);
        map.put("total", total);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getLog(LogEntity logEntity) {
        LogEntity logEntity1 = logMapper.selectById(logEntity.getId());
        if (logEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, logEntity1);
        return new APIResponse(map);
    }

    @Override
    public void exportLog(LogEntity logEntity, HttpServletResponse response) {
        Page<LogEntity> page = new Page<>(logEntity.getCurrentPage(), logEntity.getPageSize());
        List<LogEntity> list = logMapper.list(page, logEntity);
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<LogExportDto> list1 = new ArrayList<>();
        if (list.size() > 0) {
            list = list.stream().peek(a -> {
                LogExportDto logDto = new LogExportDto();
                LogExportDto logDto1 = ObjectUtil.beanConvert(logDto, a);
                logDto1.setId(a.getId());
                logDto1.setCreateTime(a.getCreateTime());
                list1.add(logDto1);
            }).collect(Collectors.toList());
        }
        try {
            ExcelUtil.exportExcel(list1, "系统日志表", "系统日志", LogExportDto.class, "系统日志",
                    response);
            //return new APIResponse();
        } catch (IOException e) {
            e.printStackTrace();
            //return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
    }

    @Override
    public APIResponse importLog(MultipartFile file) {
        try {
            List<LogImportDto> list = ExcelUtil.importExcel(file, LogImportDto.class);
            if (list == null) {
                throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
            list = list.stream().peek(a -> System.out.println(a.getOperateName())).collect(Collectors.toList());

            //TODO 数据持久化

            return new APIResponse();
        } catch (IOException e) {
            e.printStackTrace();
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
    }

}
