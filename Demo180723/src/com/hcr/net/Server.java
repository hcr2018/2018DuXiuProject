package com.hcr.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public void run(){
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			int len = is.available();
			byte[] arr=new byte[len];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
