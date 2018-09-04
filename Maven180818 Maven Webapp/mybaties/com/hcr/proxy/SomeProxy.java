package com.hcr.proxy;

/**
 * 定义静态代理类，实现目标类的接口
 * @author hcr
 * @date 2018-09-04
 */
public class SomeProxy implements ISomeService{
	
	//声明接口对象
	private ISomeService target;
	
	//声明无参构造方法
	public SomeProxy() {
		
		// TODO Auto-generated constructor stub
		//创建目标对象
		target=new ISomeSreviceImpl();
	}

	
	public String doFirst() {
		// TODO Auto-generated method stub
		String doFirst = target.doFirst();
		//增强
		String result = doFirst.toUpperCase();
		
		return result;
	}

	
	public void doSecond() {
		// TODO Auto-generated method stub
		System.out.println("执行代理--dosecond");
	}

}
