package com.hcr.bigdate.io;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.junit.Test;

public class IOEx {
	private FileFilter fnf;
	
	//在构造函数执行时调用,
	{
		fnf=name->{
			boolean b=name.isDirectory()||name.getName().endsWith(".xml");
			return b;
		};
	}
	//递归
	public void testIO(File f) {
		if (f.isFile()) {
			System.out.println(f.getName());
		} else if (f.isDirectory()) {
			System.out.println(f.getName());
			
			
	
//			fnf=new FileFilter() {
//				
//				@Override
//				public boolean accept(File name) {
//					// TODO Auto-generated method stub
//					return name.isDirectory()||name.getName().endsWith(".xml");
//				}
//		};
			
			
		
		//输出	
//		File[] listFiles = f.listFiles();
//		for (File file : listFiles) {
//			//System.out.println(file);
//			testIO(file);
//		}
		
		Arrays.stream(f.listFiles(fnf)).forEach(file->testIO(file));
		
		
		}
		
		
	}

	/**
	 * 空格
	 * 
	 * @param num
	 * @return
	 */
	public String getTable(int num) {
		String str = "";
		for (int i = 1; i < num; i++) {
			str += " ";
		}
		return str;

	}

	@Test
	public void testFilter() {
		File file = new File("d:/mysql");
		this.testIO(file);
	}

}
