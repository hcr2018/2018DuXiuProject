package com.hcr.adapter;

public interface IworkAdapter {
	//为了兼容所有员工，参数必须为Object类型
	void work(Object worker);
	//判断当前适配器是否支持指定的工种对象
	boolean supports(Object worker);
}
