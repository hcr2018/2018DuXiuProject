package com.hcr.bean;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
/**
 * xml解析后的封装类
 * @author cjx
 *
 */
public class BeanValue {
	//类名
	private String className;
	//元素节点名、value、type
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
	 * 将从xml解析出来的元素节点名、value、type添加到beanvalue中方法
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
