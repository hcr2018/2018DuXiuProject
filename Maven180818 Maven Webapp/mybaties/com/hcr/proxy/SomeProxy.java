package com.hcr.proxy;

/**
 * ���徲̬�����࣬ʵ��Ŀ����Ľӿ�
 * @author hcr
 * @date 2018-09-04
 */
public class SomeProxy implements ISomeService{
	
	//�����ӿڶ���
	private ISomeService target;
	
	//�����޲ι��췽��
	public SomeProxy() {
		
		// TODO Auto-generated constructor stub
		//����Ŀ�����
		target=new ISomeSreviceImpl();
	}

	
	public String doFirst() {
		// TODO Auto-generated method stub
		String doFirst = target.doFirst();
		//��ǿ
		String result = doFirst.toUpperCase();
		
		return result;
	}

	
	public void doSecond() {
		// TODO Auto-generated method stub
		System.out.println("ִ�д���--dosecond");
	}

}
