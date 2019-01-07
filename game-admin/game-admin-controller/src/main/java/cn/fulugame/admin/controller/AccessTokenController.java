package cn.fulugame.admin.controller;

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
@RequestMapping("/api/v1/test")
public class AccessTokenController {

    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping(value = "index")
    public Result index() {
        return Result.success().data(accessTokenService.find(1,10));
    }
    
    
    @GetMapping(value = "/testPage")
    public ModelAndView list(Model model) {
        ModelAndView view = new ModelAndView("h5_game_list");
        return view;
    }

}
