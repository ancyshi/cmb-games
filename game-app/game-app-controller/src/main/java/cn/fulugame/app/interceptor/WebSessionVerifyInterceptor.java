package cn.fulugame.app.interceptor;

import cn.fulugame.app.utils.RequestUtil;
import cn.fulugame.common.Constant;
import cn.fulugame.core.entity.utils.SubjectUtil;
import cn.fulugame.core.entity.User;
import cn.fulugame.core.service.impl.RedisOpenServiceImpl;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证web session有效性
 */
@Slf4j
@Component
public class WebSessionVerifyInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisOpenServiceImpl redisOpenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String queryparm = request.getQueryString();
        String suffix = StringUtils.isEmpty(queryparm) ? "" : "?" + queryparm;
        String url = request.getRequestURL() + suffix;
        log.info("API用户访问session拦截器:{}", url);
        String tokenId = request.getParameter("tokenId");
        if (StringUtils.isEmpty(tokenId)) {
            tokenId = request.getHeader("Authorization");
        }
        if (StringUtils.isEmpty(tokenId)) {
            Map map = invalidMap();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(JSONObject.toJSONString(map));
            return false;
        } else {
            User ud = redisOpenService.get(Constant.ADMIN_ACCESS_TOKEN, tokenId, User.class);
            boolean flag = ud != null;
            if (!flag) {
                Map map = invalidMap();
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(JSONObject.toJSONString(map));
            }
            SubjectUtil.setUser(ud);
            SubjectUtil.setIp(RequestUtil.getIpAdrress(request));
            return flag;
        }
    }

    /**
     * 构造token无效返回的Map数据
     * @return
     */
    private Map<String, String> invalidMap(){
        Map<String, String> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", "token Invalid");
        return map;
    }
}
