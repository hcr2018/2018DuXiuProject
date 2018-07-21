package com.hcr.bigdata.innerclass;

import org.junit.Test;

import com.hcr.bigdata.innerclass.InnerEx.InnerClass;
import com.hcr.bigdata.innerclass.InnerEx.StaticInner;

public class TestInnerEx {
	
	@Test
	public void testNestClass(){
		//�ⲿ��
		InnerEx innerEx = new InnerEx();
		//�����ⲿ�෽��
		innerEx.say();
		
		//�����ⲿ��Ķ���ͨ��.new�������ڲ���
		//InnerEx.InnerClass inners=innerEx.new InnerClass();
		InnerClass inners=innerEx.new InnerClass();
		//�����ڲ���ķ���
	    inners.hello();
		
	    //������̬�ڲ���
		//InnerEx.StaticInner staticInner=new InnerEx.StaticInner();
		StaticInner staticInner=new StaticInner();
		//���þ�̬�ڲ��෽��
		staticInner.say();
	}

}
