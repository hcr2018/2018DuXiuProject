package com.hcr.bigdate.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorExample {
	
	private Executor executor;//声明一个执行器

	

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}
	
	public void executeTasks(){//用执行器执行多个任务
		for (int i = 0; i < 6; i++) {
			executor.execute(new SimpleTask("task"+i));
		}
	}
	
	public static void main(String[] args) {
		ExecutorExample ex = new ExecutorExample();
		//通过工厂类创建一个带3个线程固定线程
		ex.setExecutor(Executors.newFixedThreadPool(3));
		ex.executeTasks();
	}
	
}
/**
 * 任务类
 * @author cjx
 *
 */
class SimpleTask implements Runnable{
	private String taskName;
	
	 public SimpleTask(String taskName) {
		// TODO Auto-generated constructor stub
		this.taskName = taskName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("do"+taskName+".....in thread"+Thread.currentThread().getId());
	}
	
}
