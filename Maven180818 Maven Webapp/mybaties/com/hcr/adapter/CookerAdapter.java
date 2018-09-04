package com.hcr.adapter;

public class CookerAdapter implements IworkAdapter {

	public void work(Object worker) {
		// TODO Auto-generated method stub
		System.out.println(((ICooker) worker).cook());
	}

	public boolean supports(Object worker) {
		// TODO Auto-generated method stub
		return worker instanceof ICooker;
	}

}
