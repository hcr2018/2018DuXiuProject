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
		 * 第一种方式： 用类加载器读取资源文件 适用情形：资源文件和类文件在不在同一目录都可以
		 * 注意：getResourceAsStream里的参数要写资源文件的全限定路径， 包名+文件名且开头千万不要写"/"
		 */
		// InputStream is = Files.newInputStream(Paths.get("D:",
		// "autotest.properties"));
		// properties.load(is);

		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		InputStream is = new FileInputStream(path + "/reflect.properties");
		
	//	InputStream fis = Files.newInputStream(Paths.get("D:","reflect.properties"));
	        Properties props = new Properties();
	        props.load(is);// 将文件的全部内容读取到内存中，输入流到达结尾
	        Set<String> set = props.stringPropertyNames();
			System.out.println("获取key值：");
			Stream.of(set).forEach(System.out::println);
			System.out.println("******获取属性文件value值*******");
			//set转换为字符串
			 String strKey = StringUtils.join(set.toArray(), ";");
			System.out.println("属性文件Key值："+strKey);
			
			String value = props.getProperty(strKey);
			System.out.println("属性文件value值:"+value);
			
			String[] split = strKey.split("_");			
			System.out.println("获取类名：：：："+split[0]);
		
			
			
			System.out.println("获取方法中参数类型：：：："+split[1]);
			String firstStr = split[1].substring(split[1].indexOf("(") + 1, split[1].indexOf(","));
			System.out.println("获取java.lang.String::::"+firstStr);
			
			String secStr = split[1].substring(split[1].indexOf(",") + 1, split[1].indexOf(")"));
			System.out.println("获取double值：");
			System.out.println(secStr);
			
			String[] splValue = value.split("_");
			System.out.println("获取方法值：：：："+splValue[0]);
			String  firstVal = splValue[0].substring(splValue[0].indexOf("\"") + 1, splValue[0].indexOf("\""));
			System.out.println("获取第一个参数对应的值：");
			System.out.println(firstVal); 
			
			String  secVal = splValue[0].substring(splValue[0].indexOf(",") + 1, splValue[0].indexOf(")"));
			System.out.println("获取第二个参数对应的值：");
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
			
			
			
			System.out.println("获取自定义方法：：：："+splValue[1]);
			
			System.out.println("获取自定义方法，定义的值：：：："+splValue[2]);
			
			
	
		 
	       


	}
}
