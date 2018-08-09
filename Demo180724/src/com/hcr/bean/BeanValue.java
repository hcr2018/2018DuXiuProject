package com.hcr.bean;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
/**
 * xml������ķ�װ��
 * @author cjx
 *
 */
public class BeanValue {
	//����
	private String className;
	//Ԫ�ؽڵ�����value��type
	private Map<String,SimpleEntry<String,String>> propertyValues;
	
	{
		this.propertyValues=new HashMap<String, SimpleEntry<String,String>>();
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Map<String, SimpleEntry<String, String>> getPropertyValues() {
		return propertyValues;
	}
	
	public void setPropertyValues(Map<String, SimpleEntry<String, String>> propertyValues) {
		this.propertyValues = propertyValues;
	}
	
	/**
	 * ����xml����������Ԫ�ؽڵ�����value��type��ӵ�beanvalue�з���
	 * @param name
	 * @param value
	 * @param type
	 */
	public void add(String name,String value,String type) {
		this.propertyValues.put(name,new SimpleEntry<String, String>(value, type));
	}
	
	public BeanValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
