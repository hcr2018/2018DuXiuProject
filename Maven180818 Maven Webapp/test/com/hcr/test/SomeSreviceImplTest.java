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
		//���������Ŀ�귽��
	     System.out.println(isService.doFirst());
	     isService.doSecond();
	     System.out.println("------------------------------");
	     
	     JDKProxy();
	     
	     System.out.println("____________Cglib��̬�����޽ӿڷ�ʽ_____________");
	    
	     SomeSrevice service=(SomeSrevice) new CglibFactory().createProxy();
			//isService.doFirst();
			//���������Ŀ�귽��
		     System.out.println(service.doFirst());
		     service.doSecond();
		     
		     System.out.println("____________Cglib��̬�����нӿڷ�ʽ_____________");
			    
		     ISomeService service2= (ISomeService) new CglibInterfaceFactory().createProxy();
				//isService.doFirst();
				//���������Ŀ�귽��
			     System.out.println(service2.doFirst());
			     service2.doSecond();
		}
	
	/**
	 * JDK��̬����,Ҫ��Ŀ�������ʵ�ֽӿ�
	 * ��ײ�ִ��ԭ���뾲̬������ͬ
	 */
	public static void JDKProxy(){
		final ISomeService target=new ISomeSreviceImpl();
		//isService.doFirst();
		//���������Ŀ�귽��
		ISomeService service = (ISomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(),//Ŀ����ļ�����
			   target.getClass().getInterfaces(), //Ŀ������ʵ�ֵĽӿ�
			  new InvocationHandler() {//�����ڲ���
				//proxy:�������
				//method:Ŀ�귽��
				//args:Ŀ�귽���Ĳ����б�
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// TODO Auto-generated method stub
					//����Ŀ�귽��
					Object result = method.invoke(target, args);
					//���жϻ������ָ���쳣
					if (result!=null) {
						//��ǿ����
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
