package cn.fulugame.core.dao;

import cn.fulugame.core.entity.AccessToken;
import cn.fulugame.core.entity.bo.AccessTokenBO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author ShiJiaoYun
 * @email ${email}
 * @date 2019-01-04 11:06:02
 */
@Mapper
public interface AccessTokenDao extends ICommonDao<AccessToken,Long>{

    List<AccessToken> findByParameter(AccessTokenBO accessTokenBO);

}
