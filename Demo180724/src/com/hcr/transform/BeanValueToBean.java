package com.hcr.transform;

import java.util.Map;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;

import com.hcr.bean.BeanValue;
import com.hcr.bean.Student;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * 将xml中解析出来的元素节点名、value、type从beanvalue中获取变成Bean
 * 
 * @author cjx
 * @version 2018-07-24 V1.0
 */
public class BeanValueToBean {

	public Student transform(BeanValue beanValue) throws Exception {

		String className = beanValue.getClassName();
		// 加载类
		Class clazz = Class.forName(className);
		// 创建构造函数
		Object target = clazz.newInstance();
		// 创建方法
		this.create(clazz, target, beanValue.getPropertyValues());
		return (Student) target;

	}

	// name---setName
	public void create(Class clazz, Object obj, Map<String, SimpleEntry<String, String>> map) {
		map.forEach((name, value) -> {
			// 方法名称
			String methodName = this.getMethodName(name);
			// 获得数据参数类型转化成的类对象
			Class paramClass = XMLToBeanValue.strToClass(value.getValue());
			Method method = null;
			try {
				// 得到方法
				method = clazz.getMethod(methodName, paramClass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				/**
				 * 通过invoke来调用方法
				 * 
				 * 
				 * //Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，
				 * //如果参数为基本类型数据，必须转换为相应的包装类型的对象,invoke()方法的返回值总是对象，
				 * //如果实际被调用的方法的返回类型是基本类型数据，那么invoke()方法会把它转换为相应的包装类型的对象，再将其返回
				 * receiver：该方法所在类的一个对象
				 * 
				 * args： 传入的参数 如 100，“hello”
				 */
				method.invoke(obj, XMLToBeanValue.transfToObject(paramClass, value.getKey()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	/**
	 * 将name转成setName
	 * 
	 * @param field
	 * @return
	 */
	public String getMethodName(String field) {

		return "set" + StringUtils.capitalize(field);
	}

}
