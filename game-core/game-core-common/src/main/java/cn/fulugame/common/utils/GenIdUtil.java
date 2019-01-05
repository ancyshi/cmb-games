package cn.fulugame.common.utils;

import cn.fulugame.common.properties.Config;

import java.util.UUID;

/**
 * 生成唯一性id的工具类
 */
public class GenIdUtil {

    private static String getEvnPrefix() {
        Config config = ApplicationContextRegister.getBean("configProperties",Config.class);
        return config.getEvn().getPrefix();
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String GetToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }


     public static String GetTokenWithUserId(Integer userId) {
        return GetToken() + "#" + userId;
    }

    /**
     * 生成uuid
     *
     * @return
     */
    public static String GetGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
