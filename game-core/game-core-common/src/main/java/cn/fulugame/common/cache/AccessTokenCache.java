package cn.fulugame.common.cache;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: shijiaoyun.
 * @Date: 2018/11/9 11:34.
 * @Description: 访问token数据
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AccessTokenCache implements Serializable {
    private String accessToken;//平台token
    private String openAccessToken;//第三方平台token，例：掌上生活token
    private LocalDateTime expiration;
    private Integer expiresIn;
    private Long userId;
    private String openId;
    /** 当前游戏id */
    private Long gameId;
    /** 当前渠道id */
    private Long channelId;
}
