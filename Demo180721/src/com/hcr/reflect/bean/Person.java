package com.hcr.reflect.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/**
 * Comparable<Person>:��дTreeSetʱ����Ƚ��õ�
 * Cloneable,Serializable:ǳ��¡�õ�
 * @author cjx
 *
 */
public class Person implements Comparable<Person>,Cloneable,Serializable {
	
	private String name="zhangsan";
	private double height;
	private List<Integer> list;
	
	//��̬����飬ִ�й��캯��֮ǰִ��
	{
		list=new ArrayList<>();
		list.addAll(Arrays.asList(1,2,3,4));
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
	
	public Person(String name, double height) {
		super();
		this.name = name;
		this.height = height;
		
	}
	public Person() {
		super();
		System.out.println("�޲ι��캯����ִ����");
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=");
		builder.append(name);
		builder.append(", height=");
		builder.append(height);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * hashCode��equalsʵ�ֱȽϣ���ʹ�õ����ϵ�ʱ����hashSet
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		
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
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * ��д�Ƚ�������
	 */
	@Override
	public int compareTo(Person p) {
		// TODO Auto-generated method stub
		Comparator<Person> c1 = Comparator.comparing(Person::getName);
		Comparator<Person> c2 =Comparator.comparing(Person::getHeight);
	
		Comparator<Person> com = c1.thenComparing(c2);
		return com.compare(this, p);
	}
	
	/**
	 * ǳ��¡
	 */
	@Override
	public Person clone(){
		
		try {
			//���ظ���Ŀ�¡������Cloneable
			return (Person) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public String[] get(int i,double d,String s){
		System.out.println("double��ֵ�ǣ�������"+d);
		return new String[]{"zz","g","k"};
	}
	
	

}
