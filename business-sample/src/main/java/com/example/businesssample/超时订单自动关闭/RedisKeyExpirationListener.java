package com.example.businesssample.超时订单自动关闭;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Author wangxi
 * @Time 2020/5/4 21:20
 */
//@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        // 获取到失效的 key，进行取消订单业务处理（确实比较及时的获取到了过期的订单）
        String expiredKey = message.toString();
        System.out.println(expiredKey);
    }
}
