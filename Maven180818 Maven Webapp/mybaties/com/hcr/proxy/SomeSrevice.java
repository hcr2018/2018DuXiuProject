package com.hcr.proxy;

/**
 * Cglib代理的目标类
 * @author cjx
 *
 */
public class SomeSrevice  {

	public String doFirst() {
		// TODO Auto-generated method stub
		System.out.println("执行doFirst");
		String result="abcde";
		return result;
	}

	public void doSecond() {
		// TODO Auto-generated method stub
		System.out.println("执行doSecond");
	}

}
