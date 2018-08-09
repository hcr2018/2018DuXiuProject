package com.hcr.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	private Socket socket;
	{
		try {
			 socket=new Socket("localhost",8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
	
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			int len = is.available();
			byte[] arr=new byte[len];
			if((len=is.read(arr))>0){
				System.out.println(new String(arr,0,len));
			}
			os.write("heiedo".getBytes());
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
