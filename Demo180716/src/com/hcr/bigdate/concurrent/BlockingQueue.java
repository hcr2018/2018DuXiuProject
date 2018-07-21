package com.hcr.bigdate.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue {
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
		new Thread(()->{
			//在0和100之间产生一个值
			while(true){
			//从0（包括0）到100之间（包括100，但不包括101）产生一个随机整数。
			int i = new Random().nextInt(101);
			try {
				arrayBlockingQueue.put(i);
				Thread.sleep(500);
				System.out.println("生产值："+i);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}).start();
		
		
		new Thread(()->{
			while(true){
			try {
				int i=arrayBlockingQueue.take();
				System.out.println("消费值"+i);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}).start();
	}
	

}
