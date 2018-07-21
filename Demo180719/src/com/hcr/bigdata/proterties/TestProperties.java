package com.hcr.bigdata.proterties;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Test;
/**
 * 读取属性文件
 * @author cjx
 *
 */
public class TestProperties {
	
	@Test
	public void testProter(){
	Properties pro=new Properties();
	
	try (
			InputStream is = Files.newInputStream(Paths.get("D:","Workspaces","person.properties"));
		){
		pro.load(is);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//获取属性文件的每个属性对应的属性值
	String name = pro.getProperty("name");
	String height = pro.getProperty("height");
	System.out.println(name+height);
	
	try {
		//将属性文件内容输出到xml文件中
		pro.storeToXML(Files.newOutputStream(Paths.get("D:","Workspaces","person.xml")), "这是一个xml文件");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
