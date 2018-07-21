package com.hcr.bigdate.consts;

//使用@FunctionalInterface声明，接口中只能有一个抽象方法
@FunctionalInterface
public interface PersonConsts {
	
	//在接口中声明的变量，都是public/final/static
	public static final int NUM_BER=4;
	/**
	 * 抽象方法
	 * @param content
	 * @return
	 */
	public abstract String control(String content);
	//String control(String contene);//等价于上一行代码
	
	/**
	 * 默认方法
	 * @param content
	 */
	default public void say(String content){
		
		System.out.println(content);
	}

}
