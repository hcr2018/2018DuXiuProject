package com.hcr.bigdata.innerclass;

public class InnerEx {

	private String name;

	public void say() {

		System.out.println("::这是类方法！");
	}

	public class InnerClass {
		public void hello() {
			System.out.println("::这是内部类方法");
		}
	}
	
	public static class StaticInner{
		public void say(){
			System.out.println("：：这是静态内部类方法|");
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
