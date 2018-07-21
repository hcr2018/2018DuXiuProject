package com.hcr.bigdata.bean;

import java.io.Serializable;
import java.util.Comparator;
/**
 * Comparable<Person>:在写TreeSet时排序比较用到
 * Cloneable,Serializable:浅克隆用到
 * @author cjx
 *
 */
public class Person implements Comparable<Person>,Cloneable,Serializable {
	
	private String name;
	private double height;
	private MonthEnum month;
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
	public MonthEnum getMonth() {
		return month;
	}
	public void setMonth(MonthEnum month) {
		this.month = month;
	}
	public Person(String name, double height, MonthEnum month) {
		super();
		this.name = name;
		this.height = height;
		this.month = month;
	}
	public Person() {
		super();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=");
		builder.append(name);
		builder.append(", height=");
		builder.append(height);
		builder.append(", month=");
		builder.append(month);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * hashCode和equals实现比较，在使用到集合的时候，如hashSet
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (month != other.month)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * 重写比较器方法
	 */
	@Override
	public int compareTo(Person p) {
		// TODO Auto-generated method stub
		Comparator<Person> c1 = Comparator.comparing(Person::getName);
		Comparator<Person> c2 =Comparator.comparing(Person::getHeight);
		Comparator<Person> c3 = Comparator.comparing(Person::getMonth);
		Comparator<Person> com = c1.thenComparing(c2).thenComparing(c3);
		return com.compare(this, p);
	}
	
	/**
	 * 浅克隆
	 */
	@Override
	public Person clone(){
		
		try {
			//返回父类的克隆方法，Cloneable
			return (Person) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	

}
