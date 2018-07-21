package com.hcr.bigdata.io;

import java.io.Serializable;
//io������ʵ�����л�
public class Dog implements Serializable {
	private String name;
	private transient int age; //transient�ؼ��ֽ��ֶ���Ϊ�����л��������л�����д�����̣�������ֵ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Dog(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Dog() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dog [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}
	
	
}
