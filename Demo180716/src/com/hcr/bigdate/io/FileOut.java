package com.hcr.bigdate.io;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class FileOut {
private FileFilter fnf;
	
	//在构造函数执行时调用,
	{
		fnf=name->
			name.isDirectory()||name.getName().endsWith(".xml");
	}
	public void testIO(File f,int num){
		if(f.isFile()){
			System.out.println(getTable(num-1)+f.getName());
		}
		
		else if(f.isDirectory()){
			System.out.println(getTable(num-1)+f.getName());
			Arrays.stream(f.listFiles(fnf)).forEach(file->testIO(file,num+1));
		}
	}
	
	public String getTable(int num) {
		
		return StringUtils.repeat("\t", num);

	}

	@Test
	public void test(){
		File file = new File("d:/mysql");
		this.testIO(file,1);
	}

}

	
