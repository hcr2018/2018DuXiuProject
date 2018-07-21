package com.hcr.bigdata.innerclass;

import org.junit.Test;

import com.hcr.bigdata.innerclass.InnerEx.InnerClass;
import com.hcr.bigdata.innerclass.InnerEx.StaticInner;

public class TestInnerEx {
	
	@Test
	public void testNestClass(){
		//外部类
		InnerEx innerEx = new InnerEx();
		//调用外部类方法
		innerEx.say();
		
		//利用外部类的对象通过.new来创建内部类
		//InnerEx.InnerClass inners=innerEx.new InnerClass();
		InnerClass inners=innerEx.new InnerClass();
		//调用内部类的方法
	    inners.hello();
		
	    //创建静态内部类
		//InnerEx.StaticInner staticInner=new InnerEx.StaticInner();
		StaticInner staticInner=new StaticInner();
		//调用静态内部类方法
		staticInner.say();
	}

}
