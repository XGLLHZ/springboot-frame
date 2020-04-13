package org.huangzi.main.common.utils;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:52
 * @description: 常量配置
 */
public class ConstConfig {

    /***************************项目配置************************************/

    // app_id
    public static final String APP_ID = "";
    // app_secret
    public static final String APP_SECRET = "";
    //内网 ip
    public static final String REGION = "内网IP|内网IP";
    //在线用户 redis-key
    public static final String ONLINE_KEY = "online_key";

    //返回值
    public static final String DATA_LIST = "dataList";   //列表
    public static final String TOTAL = "total";   //总数
    public static final String DATA_INFO = "dataInfo";   //详情

    //未删除
    public static final Integer DELETE_FLAG_ZONE = 0;
    //已删除
    public static final Integer DELETE_FLAG_ONE = 1;

    // 各类型
    public static final Integer TYPE_ZERO = 0;   //全部

    public static final Integer TYPE_ONE = 1;   //审核中, 博客

    public static final Integer TYPE_TWO = 2;   //未通过

    public static final Integer TYPE_THREE = 3;   //审核通过

    //日志类型
    public static final Integer ORDINARY_LOG_CODE = 1;
    public static final Integer ERROR_LOG_CODE = 2;

    /***************************基础请求************************************/

    //请求成功
    public static final Integer RE_SUCCESS_CODE = 200;
    public static final String RE_SUCCESS_MESSAGE = "请求成功！";

    //请求失败
    public static final Integer RE_ERROR_CODE = 201;
    public static final String RE_ERROR_MESSAGE = "请求失败！";

    /***************************登录、注册、注销相关*************************/

    //用户名不存在
    public static final Integer RE_USERNAME_ERROR_CODE = 100;
    public static final String RE_USERNAME_ERRORMESSAGE = "用户不存在！";

    //登录失败---用户名或密码错误
    public static final Integer RE_USERNAME_USERPWD_ERROR_CODE = 101;
    public static final String RE_USERNAME_USERPWD_ERROR_MESSAGE = "用户名或密码错误！";

    //登录失败---其它原因
    public static final Integer RE_LOGIN_ERROR_CODE = 102;
    public static final String RE_LOGIN_ERROR_MESSAGE = "登录失败！";

    //访问失败---权限不足
    public static final Integer RE_AUTHORITY_ERROR_CODE = 103;
    public static final String RE_AUTHORITY_ERROR_MESSAGE = "权限不足！";

    //注册失败-用户名已存在
    public static final Integer RE_NAME_ALREADY_EXIST_ERROR_CODE = 104;
    public static final String RE_NAME_ALREADY_EXIST_ERROR_MESSAGE = "用户名已存在！";

    //请先登录
    public static final Integer RE_PLEASE_LOGIN_FIRST_CODE = 105;
    public static final String RE_PLEASE_LOGIN_FIRST_MESSAGE = "请先登录！";

    /*****************************基础业务*******************************/

    //数据已存在
    public static final Integer RE_ALREADY_EXIST_ERROR_CODE = 110;
    public static final String RE_ALREADY_EXIST_ERROR_MESSAGE = "数据已存在！";

    //数据不存在
    public static final Integer RE_NO_EXIST_ERROR_CODE = 111;
    public static final String RE_NO_EXIST_ERROR_MESSAGE = "数据不存在！";

    //编码已存在
    public static final Integer RE_CODE_ALREADY_EXIST_ERROR_CODE = 112;
    public static final String RE_CODE_ALREADY_EXIST_ERROR_MESSAGE = "编码已存在！";

    //部门不存在
    public static final Integer RE_DEPARTMENT_NO_EXIST_ERROR_CODE = 114;
    public static final String RE_DEPARTMENT_NO_EXIST_ERROR_MESSAGE = "部门不存在！";

    //岗位不存在
    public static final Integer RE_POST_NO_EXIST_ERROR_CODE = 115;
    public static final String RE_PSOT_NO_EXIST_ERROR_MESSAGE = "岗位不存在！";

    //删除字典
    public static final Integer RE_DELETE_DICT_ERROR_CODE = 116;
    public static final String RE_DELETE_DICT_ERROR_MESSAGE = "请先删除字典详情！";

    //删除-存在子级别
    public static final Integer RE_EXIST_CHILDREN_CODE = 117;
    public static final String RE_EXIST_CHILDREN_MESSAGE = "此项存在子级，不能删除！";

    // 微信小程序授权失败
    public static final Integer RE_MA_LOGIN_FAIL_CODE = 118;
    public static final String RE_MA_LOGIN_FAIL_MESSAGE = "微信小程序授权失败！";

    //删除媒体没有权限
    public static final Integer RE_CANNOT_DEL_CODE = 119;
    public static final String RE_CANNOT_DEL_MESSAGE = "这些文件不是你传的哦！";

}
