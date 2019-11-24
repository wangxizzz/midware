package com.demo.threadpool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wangxi
 * @Time 2019/11/24 11:17
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestDemo {
    @Test
    public void test01() {
        System.out.println(3 << 1);
        String s = String.valueOf(-1 << 29);
        System.out.println(s);
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 << 29));
    }

    /**
     * 测试循环跳出
     */
    @Test
    public void test02() {
        flag:
        for (int j = 0; j < 3; j++) {
            System.out.println("j = " + j);
            for (int i = 0; i < 4; i++) {
                if (i == 2) {
                    //break flag;  // 直接跳出所有循环之外了
                }
                if (i == 2) {
                    continue flag; // 相当于break;跳出当前循环
                }
                System.out.println("i = " + i);
            }
        }
    }
}
