package cn.fulugame.core.entity.utils;


import cn.fulugame.common.cache.AccessTokenCache;
import cn.fulugame.core.entity.User;
import cn.fulugame.core.entity.Game;

import java.util.Objects;

/**
 * @Author: shijiaoyun.
 * @Date: 2018/11/9 11:20.
 * @Description: 获取和设置当前登录用户对象数据
 */
public class SubjectUtil {
    /**
     * 访问token
     */
    private static final ThreadLocal<String> accessToken = new ThreadLocal<>();
    /**  */
    private static final ThreadLocal<String> openId = new ThreadLocal<>();

    /**
     * 会员信息
     */
    private static final ThreadLocal<Long> memberId = new ThreadLocal<>();

    private static final ThreadLocal<String> memberName = new ThreadLocal<>();
    /**
     * 当前游戏id
     */
    private static final ThreadLocal<Long> gameId = new ThreadLocal<>();
    /**
     * 渠道id
     */
    private static final ThreadLocal<Long> channelId = new ThreadLocal<>();
    /**
     * 请求IP
     */
    private static final ThreadLocal<String> ip = new ThreadLocal<>();
    /**
     * 当前游戏信息
     */
    private static final ThreadLocal<Game> game = new ThreadLocal<>();

    /**
     * 当前游戏信息
     */
    private static final ThreadLocal<User> user = new ThreadLocal<>();

    /**
     * 访问token
     */
    private static final ThreadLocal<AccessTokenCache> accessTokenCache = new ThreadLocal<>();

    public  static User getUser(){return user.get();}

    public static  void  setUser(User u){user.set(u);}

    public static String getAccessToken() {
        return accessToken.get();
    }

    public static void setAccessToken(String currentAccessToken) {
        accessToken.set(currentAccessToken);
    }

    public static String getOpenId() {
        return openId.get();
    }

    public static void setOpenId(String currentOpenId) {
        openId.set(currentOpenId);
    }

    public static Long getGameId() {
        return gameId.get();
    }

    public static void setGameId(Long currentGameId) {
        gameId.set(currentGameId);
    }

    public static String getMemberName() {
        return memberName.get();
    }

    public static void setMembeName(String name) {
        memberName.set(name);
    }

    public static Long getChannelId() {
        return channelId.get();
    }

    public static void setChannelId(Long currentChannelId) {
        channelId.set(currentChannelId);
    }

    public static String getIp() {
        return ip.get();
    }

    public static void setIp(String ipStr) {
        ip.set(ipStr);
    }

    public static Game getGame() {
        return game.get();
    }

    public static void setGame(Game currentGame) {
        game.set(currentGame);
    }

    public static AccessTokenCache getAccessTokenCache() {
        return accessTokenCache.get();
    }

    public static Long getMemberId() {
        return memberId.get();
    }

    public static void setMemberId(Long currentMemberId){
        memberId.set(currentMemberId);
    }

    public static void setAccessTokenCache(AccessTokenCache currentAccessTokenCache) {
        if (Objects.isNull(currentAccessTokenCache)) {
            return;
        }
        setAccessToken(Objects.nonNull(currentAccessTokenCache.getAccessToken()) ? currentAccessTokenCache.getAccessToken() : "");
        setOpenId(Objects.nonNull(currentAccessTokenCache.getOpenId()) ? currentAccessTokenCache.getOpenId() : "");
        setChannelId(Objects.nonNull(currentAccessTokenCache.getChannelId()) ? currentAccessTokenCache.getChannelId() : 0L);
        setGameId(Objects.nonNull(currentAccessTokenCache.getGameId()) ? currentAccessTokenCache.getGameId() : 0L);
        setMemberId(Objects.nonNull(currentAccessTokenCache.getUserId()) ? currentAccessTokenCache.getUserId() : 0L);
        accessTokenCache.set(currentAccessTokenCache);
    }


}
