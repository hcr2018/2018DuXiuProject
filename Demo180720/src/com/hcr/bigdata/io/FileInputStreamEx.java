package com.hcr.bigdata.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class FileInputStreamEx {
	
	@Test
	public void testInputStream(){
		//�ֽ���
		//try()�зŶ���
		try (
			FileInputStream fis = new FileInputStream("D:\\Workspaces\\test.txt");
		    BufferedInputStream bis = new BufferedInputStream(fis);
			)
		{
			//�����ֽ��������,ÿ�δ�3���ֽ�
			byte[] by=new  byte[3];
			int len=0;
			
			while((len=bis.read(by))>0){//��ȡ���ֽ�������
				String str = new String(by, 0, len);
				System.out.println(str);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
