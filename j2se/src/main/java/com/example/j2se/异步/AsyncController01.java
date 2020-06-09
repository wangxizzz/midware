package com.example.j2se.异步;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangxi created on 2020/6/9 3:29 PM
 * @version v1.0
 *
 */
@RestController
public class AsyncController01 {

	@RequestMapping("test01")
	public void test01() {
		long start = System.currentTimeMillis();
		ExecutorService service = Executors.newFixedThreadPool(2);
		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 1;
		}, service);

		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 1;
		}, service);

		CompletableFuture.allOf(future1, future2)
				.thenRun(() -> System.out.println("done"));

		System.out.println(System.currentTimeMillis() - start);
	}
}
