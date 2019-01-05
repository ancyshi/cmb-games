package cn.fulugame.core.service.impl;


import cn.fulugame.core.dao.AccessTokenDao;
import cn.fulugame.core.dao.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.fulugame.core.entity.AccessToken;
import cn.fulugame.core.service.AccessTokenService;



@Service
public class AccessTokenServiceImpl extends AbsCommonService<AccessToken,Long> implements AccessTokenService {

    @Autowired
	private AccessTokenDao accessTokenDao;



    @Override
    public ICommonDao<AccessToken, Long> getDao() {
        return accessTokenDao;
    }
	
}
