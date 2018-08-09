package com.hcr.transform;

import java.util.Map;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;

import com.hcr.bean.BeanValue;
import com.hcr.bean.Student;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * ��xml�н���������Ԫ�ؽڵ�����value��type��beanvalue�л�ȡ���Bean
 * 
 * @author cjx
 * @version 2018-07-24 V1.0
 */
public class BeanValueToBean {

	public Student transform(BeanValue beanValue) throws Exception {

		String className = beanValue.getClassName();
		// ������
		Class clazz = Class.forName(className);
		// �������캯��
		Object target = clazz.newInstance();
		// ��������
		this.create(clazz, target, beanValue.getPropertyValues());
		return (Student) target;

	}

	// name---setName
	public void create(Class clazz, Object obj, Map<String, SimpleEntry<String, String>> map) {
		map.forEach((name, value) -> {
			// ��������
			String methodName = this.getMethodName(name);
			// ������ݲ�������ת���ɵ������
			Class paramClass = XMLToBeanValue.strToClass(value.getValue());
			Method method = null;
			try {
				// �õ�����
				method = clazz.getMethod(methodName, paramClass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				/**
				 * ͨ��invoke�����÷���
				 * 
				 * 
				 * //Method���invoke(Object obj,Object args[])�������յĲ�������Ϊ����
				 * //�������Ϊ�����������ݣ�����ת��Ϊ��Ӧ�İ�װ���͵Ķ���,invoke()�����ķ���ֵ���Ƕ���
				 * //���ʵ�ʱ����õķ����ķ��������ǻ����������ݣ���ôinvoke()���������ת��Ϊ��Ӧ�İ�װ���͵Ķ����ٽ��䷵��
				 * receiver���÷����������һ������
				 * 
				 * args�� ����Ĳ��� �� 100����hello��
				 */
				method.invoke(obj, XMLToBeanValue.transfToObject(paramClass, value.getKey()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	/**
	 * ��nameת��setName
	 * 
	 * @param field
	 * @return
	 */
	public String getMethodName(String field) {

		return "set" + StringUtils.capitalize(field);
	}

}
