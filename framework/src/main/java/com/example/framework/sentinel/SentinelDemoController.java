package com.example.framework.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxi created on 2020/11/28 17:41
 * @version v1.0
 *
 * https://blog.csdn.net/qq_41522089/article/details/107120589
 */
@RestController
public class SentinelDemoController {
    @Autowired
    private SentinelDemoService service;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable long name) throws BlockException {
        return service.sayHello(name);
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_blockHandler")//降级处理注解
    //value要与调用资源一致，降级方法设置属性是deal_blockHandler
    public String testHotkey(
            @RequestParam(value = "v1",required = false)String v1,
            @RequestParam(value = "v2",required = false)String v2) {
        return "-----------testHotkey";
    }
    //降級方法，参数类型与个数与调用方法一致,如果返回错误，说明没有加BlockException，或者导包BlockedException
    public String deal_blockHandler(String v1, String v2, BlockException excption) {
        System.out.println("=================");
        return "----------deal_blockHandlero(╥﹏╥)o";
    }
}
