package com.hcr.bigdate.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ReadAngWrite {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(10);
		Runnable writer=(()->{
			while(true){
			int next = new Random().nextInt();
			try {
				bq.put(next);

				Thread.sleep(500);
				System.out.println("生产值"+next);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		});
		
		Runnable reader=(()->{
			while(true){
			
				try {
					
					int next = bq.take();
					System.out.println("取出值"+next);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		new Thread(writer).start();
		new Thread(reader).start();
		
	}
	
	

}
