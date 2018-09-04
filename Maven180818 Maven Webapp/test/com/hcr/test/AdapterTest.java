package com.hcr.test;

import com.hcr.adapter.AliProgrammer;
import com.hcr.adapter.CookerAdapter;
import com.hcr.adapter.IworkAdapter;
import com.hcr.adapter.ProgrammerAdapter;
import com.hcr.adapter.QjdCooker;
import com.hcr.adapter.WorkAdapter;

public class AdapterTest {
	public static void main(String[] args) {
		QjdCooker cooker = new QjdCooker();
		AliProgrammer programmer = new AliProgrammer();
		
		Object[] workers={cooker,programmer};
		WorkAdapter adapter = new WorkAdapter();
		for (Object worker : workers) {
			adapter.work(worker);
		}
		
		System.out.println("----------每一个工种定义一个适配器----------------");
		for (Object worker : workers) {
			IworkAdapter adapter2=getAdapter(worker);
			adapter.work(worker);
		}
	}
	//根据工种对象获取适配器对象
	private static IworkAdapter getAdapter(Object worker) {
		// TODO Auto-generated method stub
		CookerAdapter cookerAdapter = new CookerAdapter();
		ProgrammerAdapter programmerAdapter = new ProgrammerAdapter();
		
		//获取所有适配器
		IworkAdapter[] allAdapters={cookerAdapter,programmerAdapter};
		for (IworkAdapter adapter : allAdapters) {
			if (adapter.supports(worker)) {
				return adapter;
			}
		}
		return null;
	}

}
