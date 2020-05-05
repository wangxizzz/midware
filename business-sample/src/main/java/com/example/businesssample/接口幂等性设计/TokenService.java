package com.example.businesssample.接口幂等性设计;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wangxi
 * @Time 2020/5/5 10:06
 */
public interface TokenService {
    /**
     * 创建token
     * @return
     */
    String createToken(int expireTime);

    /**
     * 检验token
     * @param request
     * @return
     */
    boolean checkToken(HttpServletRequest request) throws Exception;
}
