package com.hcr.bigdata.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class FileChannelEx {
	
	@Test
	public void testFileChannel(){
	try(
		//用通道读取文件
		FileChannel fc = new FileInputStream("D://1.txt").getChannel();
			//FileChannel.open("D:\\Workspaces\\test.txt");
			
			){
		//定义字节缓冲数组，初始容量为3
		ByteBuffer bb = ByteBuffer.allocate(3);
		byte[] arr=	new byte[3];
		while(fc.read(bb)>0){//将文件内容读到缓冲区，每读进一个进buffer，position后移一个，直到移到limit位置
			
			//使用flip后将position位置指向开始下标0，capatity：容量，指向内容的后一个位置下标,limit的位置指向原来position的位置,
			bb.flip();
			//从buffer中取数
			bb.get(arr, 0, bb.limit());
			/*bb.flip();
			byte[] arr=	new byte[3];
			//
			for (int i = 0; i < bb.limit(); i++) {
				
				//取出内容，
				arr[i]=bb.get();
			}*/
			//打印取出的内容
			System.out.println(new String(arr, 0, bb.limit()));
			//将position位置移向0
			bb.position(0);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}

	}
}
