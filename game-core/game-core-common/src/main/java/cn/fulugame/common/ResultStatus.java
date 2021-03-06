package cn.fulugame.common;

/**
 * Author: koabs
 * 8/22/16.
 * 返回结果状态
 */
public class ResultStatus {

    //接口正常返回
    public static Integer SUCCESS = 200;
    //系统内部错误,需要前端捕获的
    public static Integer ERROR = 500;
    //用户未登陆
    public static Integer NOLOGIN = 501;
    public static Integer USER_BANNED = 504;
    //没有formToken，不能提交表单
    public static Integer DATAEXCPTION = 503;
    //新用户待绑定
    public static Integer NEWUSER = 201;
    //无接口调用权限
    public static Integer ACCESS_DENY = 403;
    //版本太低
    public static Integer VERSIONS_LOW = 600;

}
