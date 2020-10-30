package com.example.bootstartermsg;

/**
 * @author wangxi03 created on 2020/10/30 10:25 AM
 * @version v1.0
 */
public class MsgService {
    private String username;

    private String password;

    MsgService(){}

    MsgService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendMsg(String content) {
        System.out.println("========自定义starter....content = " + content);
    }
}
