package com.hcr.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * CGLIB ��̬���� �нӿ�ʵ�ַ�ʽ
 * @author cjx
 *
 */
public class CglibInterfaceFactory implements MethodInterceptor {
	
	private ISomeService target;
	
	public CglibInterfaceFactory() {
		//�ڵ����޲ι��췽��ʱ�͵��ô�������
		target = new ISomeSreviceImpl();
	}

	public ISomeService  createProxy(){
		//������ǿ����
		Enhancer enhancer = new Enhancer();
		//��ʼ����ǿ������Ŀ����ָ��Ϊ����
		enhancer.setSuperclass(ISomeService.class);
		//���ûص��ӿڶ���
		enhancer.setCallback(this);
		//ʹ����ǿ������������� 
		return (ISomeService) enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy arg3) throws Throwable {
		Object result = method.invoke(target, args);
		//���жϻ������ָ���쳣
		if (result!=null) {
			//��ǿ����
			result = ((String) result).toUpperCase();
		}
		return result;
		
	}
}
