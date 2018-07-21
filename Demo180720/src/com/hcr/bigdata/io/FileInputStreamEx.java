package com.hcr.bigdata.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class FileInputStreamEx {
	
	@Test
	public void testInputStream(){
		//字节流
		//try()中放对象
		try (
			FileInputStream fis = new FileInputStream("D:\\Workspaces\\test.txt");
		    BufferedInputStream bis = new BufferedInputStream(fis);
			)
		{
			//定义字节数组接收,每次传3个字节
			byte[] by=new  byte[3];
			int len=0;
			
			while((len=bis.read(by))>0){//读取到字节数组中
				String str = new String(by, 0, len);
				System.out.println(str);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
