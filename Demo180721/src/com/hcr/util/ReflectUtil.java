package com.hcr.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;

import org.apache.commons.lang.xwork.StringUtils;

public class ReflectUtil {

	@Test
	public void testUtil() throws IOException {

		//Properties p = new Properties();
		/**
		 * ��һ�ַ�ʽ�� �����������ȡ��Դ�ļ� �������Σ���Դ�ļ������ļ��ڲ���ͬһĿ¼������
		 * ע�⣺getResourceAsStream��Ĳ���Ҫд��Դ�ļ���ȫ�޶�·���� ����+�ļ����ҿ�ͷǧ��Ҫд"/"
		 */
		// InputStream is = Files.newInputStream(Paths.get("D:",
		// "autotest.properties"));
		// properties.load(is);

		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		InputStream is = new FileInputStream(path + "/reflect.properties");
		
	//	InputStream fis = Files.newInputStream(Paths.get("D:","reflect.properties"));
	        Properties props = new Properties();
	        props.load(is);// ���ļ���ȫ�����ݶ�ȡ���ڴ��У������������β
	        Set<String> set = props.stringPropertyNames();
			System.out.println("��ȡkeyֵ��");
			Stream.of(set).forEach(System.out::println);
			System.out.println("******��ȡ�����ļ�valueֵ*******");
			//setת��Ϊ�ַ���
			 String strKey = StringUtils.join(set.toArray(), ";");
			System.out.println("�����ļ�Keyֵ��"+strKey);
			
			String value = props.getProperty(strKey);
			System.out.println("�����ļ�valueֵ:"+value);
			
			String[] split = strKey.split("_");			
			System.out.println("��ȡ������������"+split[0]);
		
			
			
			System.out.println("��ȡ�����в������ͣ�������"+split[1]);
			String firstStr = split[1].substring(split[1].indexOf("(") + 1, split[1].indexOf(","));
			System.out.println("��ȡjava.lang.String::::"+firstStr);
			
			String secStr = split[1].substring(split[1].indexOf(",") + 1, split[1].indexOf(")"));
			System.out.println("��ȡdoubleֵ��");
			System.out.println(secStr);
			
			String[] splValue = value.split("_");
			System.out.println("��ȡ����ֵ��������"+splValue[0]);
			String  firstVal = splValue[0].substring(splValue[0].indexOf("\"") + 1, splValue[0].indexOf("\""));
			System.out.println("��ȡ��һ��������Ӧ��ֵ��");
			System.out.println(firstVal); 
			
			String  secVal = splValue[0].substring(splValue[0].indexOf(",") + 1, splValue[0].indexOf(")"));
			System.out.println("��ȡ�ڶ���������Ӧ��ֵ��");
			System.out.println(secVal); 
			                                                                                      
			/*try {
				String clszzName=split[0];
				Class<?> clazz = Class.forName(clszzName);
				Annotation[] annotations = clazz.getAnnotations();
				for (Annotation annotation : annotations) {
					System.out.println(annotation);
				}
			} catch (SecurityException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			
			System.out.println("��ȡ�Զ��巽����������"+splValue[1]);
			
			System.out.println("��ȡ�Զ��巽���������ֵ��������"+splValue[2]);
			
			
	
		 
	       


	}
}
