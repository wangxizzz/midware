package com.example.businesssample.接口幂等性设计;

import com.example.businesssample.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangxi
 * @Time 2020/5/5 10:07
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    /**
     * token的服务实现类：token引用了redis服务，创建token采用随机算法工具类生成随机uuid字符串,
     * 然后放入到redis中(为了防止数据的冗余保留,这里设置过期时间为10000秒,具体可视业务而定)，
     * 如果放入成功，最后返回这个token值。checkToken方法就是从header中获取token到值(如果header中拿不到，
     * 就从paramter中获取)，如若不存在,直接抛出异常。这个异常信息可以被拦截器捕捉到，然后返回给前端。
     */

    @Resource
    private RedisService redisService;

    @Override
    public String createToken(int expireTime) {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        try {
            token.append(Constant.Redis.TOKEN_PREFIX).append(str);
            redisService.setEx(token.toString(), token.toString(), expireTime);
            boolean notEmpty = StringUtils.isNotEmpty(token.toString());
            if (notEmpty) {
                return token.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {

        String token = request.getHeader(Constant.TOKEN_NAME);
        // header中不存在token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(Constant.TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
                log.error("检查token失败,请求头中未带token");
                return false;
            }
        }

        if (!redisService.exists(token)) {
            log.error("redis中不存在此token，判定为重复提交");
            return false;
        }
        boolean remove = redisService.remove(token);
        if (!remove) {
            log.error("redis中不存在此token，判定为重复提交");
            return false;
        }
        log.info("token验证成功，放过请求");
        return true;
    }
}
