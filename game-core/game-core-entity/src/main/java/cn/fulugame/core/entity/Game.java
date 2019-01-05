package cn.fulugame.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: shijiaoyun.
 * @Date: 2019/1/4 15:02.
 * @Description:
 */
@Data
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    /**
     * 游戏名
     */
    private String name;
    /**
     * 游戏秘钥
     */
    private String secretKey;

    /**
     * 游戏地址
     */
    private String url;

    /**
     * 后台地址
     */
    private String backgroundUrl;
    /**
     * 后台账号
     */
    private String backgroundUsername;

    /**
     * 后台密码
     */
    private String backgroundPassword;

    /**
     * 游戏回调URl
     */
    private String callbackUrl;

    /**
     * 是否补单 1需要 0不需要
     */
    private Integer isRepair;

    private String remark;

    /**
     * 日志类型,0接入中,1运营中,2已下线
     */
    private Integer state;

    /** 招行给游戏分配的应用id */
    private String appId;

    /**
     * 渠道ID
     */
    private Long channelId;

    private String copyUrl;

    private List<Long> resIds;
}
