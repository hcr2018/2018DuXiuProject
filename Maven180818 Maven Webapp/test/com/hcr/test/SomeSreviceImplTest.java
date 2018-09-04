package com.hcr.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.hcr.proxy.CglibFactory;
import com.hcr.proxy.CglibInterfaceFactory;
import com.hcr.proxy.ISomeService;
import com.hcr.proxy.ISomeSreviceImpl;
import com.hcr.proxy.SomeProxy;
import com.hcr.proxy.SomeSrevice;


public class SomeSreviceImplTest {
	public static void main(String[] args) {
		ISomeService isService=new SomeProxy();
		//isService.doFirst();
		//代理类调用目标方法
	     System.out.println(isService.doFirst());
	     isService.doSecond();
	     System.out.println("------------------------------");
	     
	     JDKProxy();
	     
	     System.out.println("____________Cglib动态代理：无接口方式_____________");
	    
	     SomeSrevice service=(SomeSrevice) new CglibFactory().createProxy();
			//isService.doFirst();
			//代理类调用目标方法
		     System.out.println(service.doFirst());
		     service.doSecond();
		     
		     System.out.println("____________Cglib动态代理：有接口方式_____________");
			    
		     ISomeService service2= (ISomeService) new CglibInterfaceFactory().createProxy();
				//isService.doFirst();
				//代理类调用目标方法
			     System.out.println(service2.doFirst());
			     service2.doSecond();
		}
	
	/**
	 * JDK动态代理,要求目标类必须实现接口
	 * 其底层执行原理与静态代理相同
	 */
	public static void JDKProxy(){
		final ISomeService target=new ISomeSreviceImpl();
		//isService.doFirst();
		//代理类调用目标方法
		ISomeService service = (ISomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(),//目标类的加载器
			   target.getClass().getInterfaces(), //目标类所实现的接口
			  new InvocationHandler() {//匿名内部类
				//proxy:代理对象
				//method:目标方法
				//args:目标方法的参数列表
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// TODO Auto-generated method stub
					//调用目标方法
					Object result = method.invoke(target, args);
					//不判断会产生空指针异常
					if (result!=null) {
						//增强方法
						result = ((String) result).toUpperCase();
					}
					return result;
				}
			});
		String result = service.doFirst();
		System.out.println(result);
	   service.doSecond();
	}
	
	
}
