package com.hcr.adapter;

public interface IworkAdapter {
	//Ϊ�˼�������Ա������������ΪObject����
	void work(Object worker);
	//�жϵ�ǰ�������Ƿ�֧��ָ���Ĺ��ֶ���
	boolean supports(Object worker);
}
