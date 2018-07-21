package com.hcr.bigdate.forkjointask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.Test;

public class TestCaltor {
	@Test
	public void run()throws Exception{
		//创建线程池
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		//在线程池中进行计算
		ForkJoinTask<Integer> submit = forkJoinPool.submit(new Calculator(0,10000));
		if(!submit.isDone()){
			Thread.sleep(100);			
		}
		System.out.println(submit.get());
		forkJoinPool.shutdown();//关闭线程池
	}

}
