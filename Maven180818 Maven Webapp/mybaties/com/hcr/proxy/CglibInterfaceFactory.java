package com.hcr.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * CGLIB 动态代理 有接口实现方式
 * @author cjx
 *
 */
public class CglibInterfaceFactory implements MethodInterceptor {
	
	private ISomeService target;
	
	public CglibInterfaceFactory() {
		//在调用无参构造方法时就调用创建对象
		target = new ISomeSreviceImpl();
	}

	public ISomeService  createProxy(){
		//创建增强对象
		Enhancer enhancer = new Enhancer();
		//初始化增强器，将目标类指定为父类
		enhancer.setSuperclass(ISomeService.class);
		//设置回调接口对象
		enhancer.setCallback(this);
		//使用增强器创建代理对象 
		return (ISomeService) enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy arg3) throws Throwable {
		Object result = method.invoke(target, args);
		//不判断会产生空指针异常
		if (result!=null) {
			//增强方法
			result = ((String) result).toUpperCase();
		}
		return result;
		
	}
}
