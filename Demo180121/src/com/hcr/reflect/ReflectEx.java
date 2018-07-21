package com.hcr.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.Test;

import com.hcr.reflect.bean.Person;
/**
 * ���÷�����Ի�ȡ�๹�캯���Ĳ���������Ϣ����һ����������Խ��и�ֵ
 * @author cjx
 *
 */
public class ReflectEx {
	
	/**
	 * ȡ������ඨ��ķ���
	 */
	@Test
	public void testMethod(){
		
	Class<Person>	clazz= Person.class;
	//��ȡ����
	Method[] methods = clazz.getDeclaredMethods();
	Stream.of(methods).forEach(m->{
		//��ȡ������
		System.out.println("��������"+m.getName());
		//��ȡ���η�Ȩ��
		System.out.println("���η���"+m.getModifiers());
	});
	}
	
	/**
	 * ȡ����������
	 */
	@Test
	public void testPublicMethod(){
		try {
			Stream.of(Class.forName("com.hcr.reflect.bean.Person").getMethods()).forEach(System.out::println);
		} catch (SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvoke() throws Exception{
		Class<Person> clazz=Person.class;
		Person p = (Person) clazz.newInstance();
		System.out.println("**************************��Ա����*****");
		Method method = clazz.getMethod("getName");
		String obj = (String) method.invoke(p, null);
		System.out.println(obj);
		System.out.println("************��Ա����***********888");
		Method getMethod = clazz.getMethod("get", int.class,double.class,String.class);
		String[] res = (String[]) getMethod.invoke(p, 1,2_3_4_8_9_4.e-5,"dofg");
		Stream.of(res).forEach(System.out::println);
	}
	
	
	@Test
	public void testConstructor() throws Exception{
		/* */
		/** ����ʵ����Class�ķ��� *//*
          Class  class1 = Class.forName("com.yd.test.javareflect.ReflectClass");
            // java�������κ�һ��java������getClass ����
          Class  class2 = new ReflectClass().getClass();
            //java��ÿ�����Ͷ���class ����
           Class class3 = ReflectClass.class;
       
        */
		
		//����أ�person���ڴ���
		Class< Person> clazz=Person.class;
		//��̬������
		//Class.forName("com.hcr.reflect.bean.Person");
		System.out.println("*************�޲ι��캯��************8");
		Constructor<Person> cons =  clazz.getConstructor();
		//��������
		Person p = cons.newInstance();
		System.out.println(p);
		
		System.out.println("***************�вι��캯��****************");
		Constructor<Person> cons2 = clazz.getConstructor(String.class,double.class);
		Person p2 = cons2.newInstance("lidd",3.);
		System.out.println(p2);
	}

}
