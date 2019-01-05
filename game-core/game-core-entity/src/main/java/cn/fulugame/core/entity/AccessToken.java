package cn.fulugame.core.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * 
 * 
 * @author ShiJiaoYun
 * @date 2019-01-04 11:06:02
 */
@Data
public class AccessToken implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String accessToken;
	//
	private Date expiration;
	//
	private Integer expiresIn;
	//
	private String refreshToken;
	//
	private Long userId;
	//
	private String openId;

}
