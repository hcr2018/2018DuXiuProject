package com.hcr.test;

import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.hcr.util.MyTestTools;

public class TestReflectTools {
	private MyTestTools mtt;
	@Before
	public void init(){
		mtt =new MyTestTools();
	}
	
	@Test
	public void testProperties(){
		Properties pro = mtt.getPro();
		System.out.println(pro);
		Set<String> set = pro.stringPropertyNames();
		//set.forEach(name->System.out.println(name));
		Pattern pattern=Pattern.compile("(.+)_\\((.*)\\)");
		Pattern pattern2=Pattern.compile("\\((.*)\\)_(.+)\\((.*)\\)_\\((.*)\\)");
		set.forEach((name)->{
			Matcher matcher=pattern.matcher( name);
			if(matcher.find()){
				System.out.print("类名:"+matcher.group(1) +"\t构造函数的参数类型："+matcher.group(2));
			}
			String value=pro.getProperty(name);
			
			Matcher matcher2=pattern2.matcher(value);
			if(matcher2.find()){
				System.out.println("\t\t"+matcher2.group(1)+"\t"+matcher2.group(2)+"\t"+matcher2.group(3)+"\t"+matcher2.group(4));
			}
			//System.out.println("\t\t"+name+"\t"+value);
		});
		//System.out.println(pro);

	}
	
	@Test
	public void testSplit(){
		String s="aa,bb,cc,dd";
		Stream.of(s.split(",")).forEach(System.out::println);
	}
	
	@Test
	public void testClass(){
		Class clazz = null;
		try {
			clazz = Class.forName("double");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(clazz);
	}

	@Test
	public void testAutoExcute(){
		mtt.run();
	}
}
