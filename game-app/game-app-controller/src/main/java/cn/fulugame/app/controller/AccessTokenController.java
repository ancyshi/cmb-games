package cn.fulugame.app.controller;

import cn.fulugame.common.Result;
import cn.fulugame.core.service.AccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shijiaoyun.
 * @Date: 2019/1/4 10:55.
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/v1/test")
public class AccessTokenController {

    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping(value = "index")
    public Result index() {
        return Result.success().data(accessTokenService.find(1,10));
    }

}
