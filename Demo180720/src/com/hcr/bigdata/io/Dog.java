package com.hcr.bigdata.io;

import java.io.Serializable;
//io操作需实现序列化
public class Dog implements Serializable {
	private String name;
	private transient int age; //transient关键字将字段设为非序列化，非序列化不会写到磁盘，读不出值
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
