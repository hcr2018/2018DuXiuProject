package com.hcr.bigdata.proterties;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Test;
/**
 * ��ȡ�����ļ�
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
	//��ȡ�����ļ���ÿ�����Զ�Ӧ������ֵ
	String name = pro.getProperty("name");
	String height = pro.getProperty("height");
	System.out.println(name+height);
	
	try {
		//�������ļ����������xml�ļ���
		pro.storeToXML(Files.newOutputStream(Paths.get("D:","Workspaces","person.xml")), "����һ��xml�ļ�");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
