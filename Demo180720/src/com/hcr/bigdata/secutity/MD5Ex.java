package com.hcr.bigdata.secutity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class MD5Ex {
	@Test
	public void testMySimpleMD5(){
		
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		byte[] arr=null;
		try {
			arr = Files.readAllBytes(Paths.get("D:","Workspaces","xaogou.ser"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(arr);
		byte[] result = md.digest();
		StringBuilder builder = new StringBuilder();
		for (byte b : result) {
			String str = Integer.toHexString(b);
			builder.append(str);
		}
		System.out.println(builder);
	}
	
	
	@Test
	public void testMyComplexMD5(){
		
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream("D:/Workspaces/xaogou.ser");
			byte[] arr=new byte[4];
			int len=-1;
			while((len=fis.read(arr))>0){
				md.update(arr,0,len);
				System.out.println(new String(arr,0,len));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] result = md.digest();
		StringBuilder builder = new StringBuilder();
		for (byte b : result) {
			String str = Integer.toHexString(b);
			builder.append(str);
		}
		System.out.println(builder);
		
	}
	
	@Test
	public void testDigestStream(){
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			InputStream is = Files.newInputStream(Paths.get("D:","Workspaces","xaogou.ser"));
			DigestInputStream dis = new DigestInputStream(is, md);
			byte[] arr=new byte[5];
			int len=-1;
			while((len=dis.read(arr))>0){
				
				System.out.println(new String(arr,0,len));
			}
			byte[] digest = md.digest();
			StringBuilder builder = new StringBuilder();
			for (byte b : digest) {
				String str = Integer.toHexString(b);
				builder.append(str);
			}
			System.out.println(builder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
