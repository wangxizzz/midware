package com.example.businesssample;

import com.google.common.collect.Sets;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangxi created on 2020/5/26 23:53
 * @version v1.0
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public void test() {
        System.out.println("进入方法====================");
        long start = System.currentTimeMillis();
        int count = 55000000;
        Set<Long> set1 = new HashSet<>(count);
        Set<Long> set2 = new HashSet<>(count);

        for (long i = 0; i < count; i++) {
            set1.add(i);
        }

        for (long i = count; i < count*2; i++) {
            set2.add(i);
        }

        Sets.SetView<Long> union = Sets.union(set1, set2);
        System.out.println(union.size());

        System.out.println(System.currentTimeMillis() - start);
    }

}
