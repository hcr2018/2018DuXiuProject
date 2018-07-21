package com.hcr.bigdate.datatype;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class DateEx {
	@Test
	public void testArr() throws IOException{
		//按行读取文件
		List<String> lines = Files.readAllLines(Paths.get("D:","Workspaces","00.txt"),Charset.forName("utf-8"));
		//16位65536
	int[] arr=	new int[65536];
	lines.forEach(line->{
		for (char ch : line.toCharArray()) {//遍历一个个字符串
			//将字符放数组中，ch-1为字符串内容，从数组中看为第ch-1位。
			//得出一个个字符串.计算字符次数，数组下标从0开始+1
			arr[ch-1]=arr[ch-1]+1;
		}
		
	});
	
	for (int i = 0; i < arr.length; i++) {
		//将数组转成字符串
		char ch=(char) (i+1);
		if(arr[i]>0){
			System.out.println(ch+"出现了"+arr[i]+"次");
		}
	}
			
		
	}
}
