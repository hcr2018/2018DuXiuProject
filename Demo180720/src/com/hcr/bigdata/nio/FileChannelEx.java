package com.hcr.bigdata.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class FileChannelEx {
	
	@Test
	public void testFileChannel(){
	try(
		//��ͨ����ȡ�ļ�
		FileChannel fc = new FileInputStream("D://1.txt").getChannel();
			//FileChannel.open("D:\\Workspaces\\test.txt");
			
			){
		//�����ֽڻ������飬��ʼ����Ϊ3
		ByteBuffer bb = ByteBuffer.allocate(3);
		byte[] arr=	new byte[3];
		while(fc.read(bb)>0){//���ļ����ݶ�����������ÿ����һ����buffer��position����һ����ֱ���Ƶ�limitλ��
			
			//ʹ��flip��positionλ��ָ��ʼ�±�0��capatity��������ָ�����ݵĺ�һ��λ���±�,limit��λ��ָ��ԭ��position��λ��,
			bb.flip();
			//��buffer��ȡ��
			bb.get(arr, 0, bb.limit());
			/*bb.flip();
			byte[] arr=	new byte[3];
			//
			for (int i = 0; i < bb.limit(); i++) {
				
				//ȡ�����ݣ�
				arr[i]=bb.get();
			}*/
			//��ӡȡ��������
			System.out.println(new String(arr, 0, bb.limit()));
			//��positionλ������0
			bb.position(0);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}

	}
}
