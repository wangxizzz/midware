package com.example.businesssample.接口幂等性设计;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author wangxi
 * @Time 2020/5/5 10:47
 */
@RestController
public class BusinessController {
    @Resource
    private TokenService tokenService;

    @PostMapping("/get/token")
    public String  getToken(){
        String token = tokenService.createToken(60);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        return StringUtils.EMPTY;
    }


    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public String testIdempotence() {
        return "";
    }
}
