package com.example.businesssample.接口幂等性设计;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author wangxi
 * @Time 2020/5/5 10:44
 * <p>
 * 拦截处理器：主要的功能是拦截扫描到AutoIdempotent到注解到方法,然后调用tokenService的checkToken()方法校验token是否正确，
 * 如果捕捉到异常就将异常信息渲染成json返回给前端
 */
@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    /**
     * 预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //被ApiIdempotment标记的扫描
        AutoIdempotent methodAnnotation = method.getAnnotation(AutoIdempotent.class);
        if (methodAnnotation != null) {
            try {
                return tokenService.checkToken(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
            } catch (Exception ex) {
//                ResultVo failedResult = ResultVo.getFailedResult(101, ex.getMessage());
//                writeReturnJson(response, JSONUtil.toJsonStr(failedResult));
                throw ex;
            }
        }
        //必须返回true,否则会被拦截一切请求
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 返回的json值
     * @param response
     * @param json
     * @throws Exception
     */
//    private void writeReturnJson(HttpServletResponse response, String json) throws Exception{
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=utf-8");
//        try {
//            writer = response.getWriter();
//            writer.print(json);
//
//        } catch (IOException e) {
//        } finally {
//            if (writer != null)
//                writer.close();
//        }
//    }

}
