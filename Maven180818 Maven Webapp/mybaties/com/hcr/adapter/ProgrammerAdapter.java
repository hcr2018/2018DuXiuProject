package com.hcr.adapter;

public class ProgrammerAdapter implements IworkAdapter {

	public void work(Object worker) {
		// TODO Auto-generated method stub
		System.out.println(((IProgranmer) worker).program());
	}

	public boolean supports(Object worker) {
		// TODO Auto-generated method stub
		return worker instanceof IProgranmer;
	}

}
