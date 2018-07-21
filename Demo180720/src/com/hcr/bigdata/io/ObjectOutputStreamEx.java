package com.hcr.bigdata.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

import org.junit.Test;

public class ObjectOutputStreamEx {
	
	@Test
	public void testObjectOutStream(){
		try(FileOutputStream fos = new FileOutputStream("D:\\Workspaces\\xaogou.ser");
			BufferedOutputStream bos=	new BufferedOutputStream(fos);
			ObjectOutputStream oos=	new ObjectOutputStream(bos);
				)
		{
			Dog dog = new Dog("dogdog", 25);
			oos.writeObject(dog);
			Dog dog2 = new Dog("hahhe", 36);
			oos.writeObject(dog2);
			oos.writeInt(20);
			oos.writeChars("ÄãÊÇÐ¡¹·");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testObjectIutputStream(){
		try (FileInputStream fis = new FileInputStream("D:\\Workspaces\\xaogou.ser");
				BufferedInputStream bis=new BufferedInputStream(fis);
			ObjectInputStream ois=	new ObjectInputStream(bis);
			)
		{
			System.out.println(ois.readObject());
			System.out.println(ois.readInt());
			System.out.println(ois.readChar());
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
