package com.hcr.bigdate.forkjointask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import org.junit.Test;
/**
 * Fork/join框架
 * @author cjx
 *有返回值用RecursiveTask<V>
 *无返回值用RecursiveTask
 */
public class Calculator extends RecursiveTask<Integer> {
	
	private  static final int THRESHOLD=100;//任务阈值
	private int start;
	private int end;
	
	public Calculator(int start,int end){
		this.start=start;
		this.end=end;
	}
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		int sum=0;
		//如果任务量小于阈值，就直接计算
		if((start-end<THRESHOLD)){
			for (int i = start; i < end; i++) {
				sum=sum+i;
			}
		}else{//如果大于阈值，就把他拆分成两个子任务进行fork
			int middle=(start+end)/2;
			Calculator left = new Calculator(start, middle);//一部分小线程
			left.fork();//开启这小部分线程
			Calculator right = new Calculator(middle+1, end);			
			right.fork();
			//left.join()---让left任务完整完成
			//right.join()---让right任务完整完成
			sum=left.join()+right.join();
		}
		return sum;
		
	}
	
	
}



	
