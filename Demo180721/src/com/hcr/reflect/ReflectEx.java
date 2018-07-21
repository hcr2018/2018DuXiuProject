package com.hcr.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.Test;

import com.hcr.reflect.bean.Person;
/**
 * 利用反射可以获取类构造函数的参数类型信息、对一个对象的属性进行赋值
 * @author cjx
 *
 */
public class ReflectEx {
	
	/**
	 * 取出这个类定义的方法
	 */
	@Test
	public void testMethod(){
		
	Class<Person>	clazz= Person.class;
	//获取方法
	Method[] methods = clazz.getDeclaredMethods();
	Stream.of(methods).forEach(m->{
		//获取方法名
		System.out.println("方法名："+m.getName());
		//获取修饰符权限
		System.out.println("修饰符："+m.getModifiers());
	});
	}
	
	/**
	 * 取出公共方法
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
		System.out.println("**************************成员变量*****");
		Method method = clazz.getMethod("getName");
		String obj = (String) method.invoke(p, null);
		System.out.println(obj);
		System.out.println("************成员方法***********888");
		Method getMethod = clazz.getMethod("get", int.class,double.class,String.class);
		String[] res = (String[]) getMethod.invoke(p, 1,2_3_4_8_9_4.e-5,"dofg");
		Stream.of(res).forEach(System.out::println);
	}
	
	
	@Test
	public void testConstructor() throws Exception{
		/* */
		/** 三种实例化Class的方法 *//*
          Class  class1 = Class.forName("com.yd.test.javareflect.ReflectClass");
            // java语言中任何一个java对象都有getClass 方法
          Class  class2 = new ReflectClass().getClass();
            //java中每个类型都有class 属性
           Class class3 = ReflectClass.class;
       
        */
		
		//类加载，person在内存中
		Class< Person> clazz=Person.class;
		//动态加载类
		//Class.forName("com.hcr.reflect.bean.Person");
		System.out.println("*************无参构造函数************8");
		Constructor<Person> cons =  clazz.getConstructor();
		//创建对象
		Person p = cons.newInstance();
		System.out.println(p);
		
		System.out.println("***************有参构造函数****************");
		Constructor<Person> cons2 = clazz.getConstructor(String.class,double.class);
		Person p2 = cons2.newInstance("lidd",3.);
		System.out.println(p2);
	}

}
