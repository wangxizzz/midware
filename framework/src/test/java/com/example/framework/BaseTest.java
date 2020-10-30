package com.example.framework;

import com.example.bootstartermsg.MsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wangxi03 created on 2020/10/30 11:26 AM
 * @version v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    @Resource
    private MsgService msgService;

    @Test
    public void starterTest() {

        System.out.println(msgService);
        msgService.sendMsg("adsadsadsa");
    }
}
