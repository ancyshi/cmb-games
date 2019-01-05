package cn.fulugame.common.enums;

import cn.fulugame.common.properties.Config;
import cn.fulugame.common.utils.ApplicationContextRegister;

/**
 * redis key 前缀
 */
public enum RedisKeyEnum {

    MEMBER_INFO; //会员信息


    public static final String SPLIT = "-";

    public String generateKey(int id) {
        return getEvnPrefix() + SPLIT + this.name() + SPLIT + id;
    }

    public String generateKey(String id) {
        return getEvnPrefix() + SPLIT + this.name() + SPLIT + id;
    }

    public String generateKey() {
        return getEvnPrefix() + SPLIT + this.name();
    }

    /**
     * 获取环境定义的前缀
     * @return
     */
    private String getEvnPrefix() {
        Config config = ApplicationContextRegister.getBean("configProperties",Config.class);
        return config.getEvn().getPrefix();
    }


}
