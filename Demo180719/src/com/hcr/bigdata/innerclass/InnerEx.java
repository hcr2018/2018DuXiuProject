package com.hcr.bigdata.innerclass;

public class InnerEx {

	private String name;

	public void say() {

		System.out.println("::�����෽����");
	}

	public class InnerClass {
		public void hello() {
			System.out.println("::�����ڲ��෽��");
		}
	}
	
	public static class StaticInner{
		public void say(){
			System.out.println("�������Ǿ�̬�ڲ��෽��|");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InnerEx() {
		super();
		
	}

}
