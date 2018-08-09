package com.hcr.bean;

public class Student {
	private String name;
	private double height;
	private boolean sex;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Student(String name, double height, boolean sex) {
		super();
		this.name = name;
		this.height = height;
		this.sex = sex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [name=").append(name).append(", height=").append(height).append(", sex=").append(sex)
				.append("]");
		return builder.toString();
	}
}
