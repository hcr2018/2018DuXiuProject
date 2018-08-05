package com.hcr.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTestTools {
	
//	private String fileName;
	private Properties pro;
	private Class clazz;
	private Constructor cons;
	private Method method;
	private Class[] consParams;
	private Class[] methodParams;
	private Object[] consValues;
	private Object[] methodValues;
	private Object target;
	

	public MyTestTools(String fileName) {		
	
		init(fileName);
	}

	public MyTestTools() {
		
		init("D:\\Workspaces\\reflect.properties");
	}
	
	/**
	 * 方法重构
	 * @param fileName
	 */
	private void init(String fileName) {
	
		 pro = new Properties();		
		try( InputStream is = Files.newInputStream(Paths.get(fileName));) {
			 pro.load(is);
			//System.out.println(pro);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Properties getPro(){

		return this.pro;
		 
	 }
	
	public void run(){
		Properties pro=getPro();
		//获取属性文件的Key
		Set<String> set = pro.stringPropertyNames();
		set.forEach(key->{
			//获取key对应的value值
			String value = pro.getProperty(key);
			//解析
			analysis(key, value);
		});
	}
	/**
	 * @descript 解析属性文件中的构造函数和方法的参数类型和值
	 * @param name 属性文件的key
	 * @param value 属性文件的value
	 */
	public void analysis(String name,String value){
		String className=null;
		 String consParamType="";
		 String consParamValue=null;
		 String methodName=null;
		 String methodParamType=null ;
		 String methodParamValue=null;
		//解析类名和构造函数参数
		Pattern pattern = Pattern.compile("(.+)_\\((.*)\\)");
		//匹配属性文件的key串
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			//分组，获得类名
		 className = matcher.group(1);
		 //获得构造函数参数类型
		 consParamType = matcher.group(2);		 
		}
		//编译分组
		Pattern pattern2 = Pattern.compile("\\((.*)\\)_(.+)\\((.*)\\)_\\((.*)\\)");
		//匹配属性文件的value
		Matcher matcher2 = pattern2.matcher(value);
		if(matcher2.find()){
			//获取构造函数参数类型对应的值
			 consParamValue = matcher2.group(1);
			 //方法名
			 methodName = matcher2.group(2);
			 //获取方法参数类型
			 methodParamType = matcher2.group(3);
			 //获取方法参数对应的值
			 methodParamValue = matcher2.group(4);
		}
		
		 try {
			 //反射加载类
			this.clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		    makeCons(consParamType);//获取构造函数参数类型
			this.makeConsObject(consParamValue);//获取参数对应的值
			this.makeTarget();//对象创建
			this.methodParams=this.getParamClasses(methodParamType);
			getMethod(methodName,methodParamType);//创建Method
			makeMethodParams(methodParamValue);
			execute();//执行
			/*
			 * 执行里边的方法：
			 *    1、Method 2、参数值构成的数组 
			 *  Method Class 方法名字  参数列表(Class...)
			 *  
			 *  执行：
			 *    method  目标对象  参数值对象构成的数组
			 */

		 
		
	}
	
	
	public void execute() {
		try {
			/*System.out.println("113113113:::"+(this.method ==null));
			System.out.println("113113113:::"+(this.target ==null));
			System.out.println("113113113:::"+(this.methodValues ==null));*/
			this.method.invoke(this.target, methodValues);
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getMethod(String methodName,String paramType){
		try {
			this.method=this.clazz.getMethod(methodName, getParamClasses(paramType));
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过传入的参数类型得到数据类型对应 的类
	 * @param paramType 参数类型
	 * @return 数据类型对应 的类
	 */
	 public Class[] getParamClasses(String paramType){
		 Class[] classes=null;
		 //没有参数类型
		 if(paramType==null||"".equals(paramType.trim())){
			 //返回无参对象
			 classes=new Class[]{};
		 }else{
			 //有参，截取出各参数类型
			 String[] arr = paramType.split(",");
			classes= new Class[arr.length];
			for (int i = 0; i < arr.length; i++) {
				//调用transform(参数类型)方法将数据类型转成对应 的类
				classes[i]=this.transform(arr[i]);
			}
		 }
		return classes;
	 }
	 
	 /**
	  * 通过参数类型生成函数方法
	  * @param paramType 参数类型
	  */
	 public void makeCons(String paramType){
		 //无参
		 if(paramType==null||"".equals(paramType.trim())){
			 //获取无参构造函数
			this.clazz.getConstructors();
		 }else{
			 //有参，截取出各参数类型
			 String[] arr = paramType.split(",");
			this.consParams= new Class[arr.length];
			for (int i = 0; i < arr.length; i++) {
				this.consParams[i]=this.transform(arr[i]);
			}
			try {
				//获取有参构造函数
				this.cons=clazz.getConstructor(consParams);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 
	 /**
	  * 
	  * @param types 参数类型对应的转换类
	  * @param consString  参数类型对应的值
	  * @return 值
	  */
	 public Object[] getParamValues(Class[] types,String consString){
			Object[] paramValues=null;
			//无值
			if(consString==null || "".equals(consString.trim())){
				paramValues=new Object[]{};
			}else{
				//有值，截取出各类型的值
				String arr[]=consString.split(",");
				paramValues=new Object[arr.length];
				for(int i=0;i<arr.length;i++){
					paramValues[i]=this.transform(types[i], arr[i]);
				}
			}
			
			return paramValues;
		}

	 /**
	  * 
	  * @param consString  构造函数的参数对应的值
	  */
	 public void makeConsObject(String consString){
			/*if(consString==null || "".equals(consString.trim())){
				this.consValues=new Object[]{};
			}else{
				String arr[]=consString.split(",");
				this.consValues=new Object[arr.length];
				for(int i=0;i<arr.length;i++){
					this.consValues[i]=this.transform(this.consParams[i], arr[i]);
				}
			}*/
		 //
		 this.consValues=this.getParamValues(consParams, consString);
		}
	 
	 /**
	  * 获得方法的值
	  * @param consString 方法的参数对应的值
	  */
	 public void makeMethodParams(String consString){
			this.methodValues=getParamValues(this.methodParams,consString);
		}

	 
	 public void makeTarget(){
		
				try {
					//创建对象
					this.target=this.cons.newInstance(consValues);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

	 }
	
	public Object transform(Class cladd,String value){
		Object obj=null;
		if(cladd==String.class){
			obj=value;
		}else if(cladd==int.class){
			obj=Integer.valueOf(value);
		}else if(cladd==double.class){
			obj=Double.valueOf(value);
		}else if(cladd==float.class){
			obj=Float.valueOf(value);
		}else if(cladd==char.class){
			obj=value.charAt(0);
		}else if(cladd==byte.class){
			obj=Byte.valueOf(value);
		}else if(cladd==short.class){
			obj=Short.valueOf(value);
		}else if(cladd==long.class){
			obj=Long.valueOf(value);
		}else if(cladd==boolean.class || cladd==Boolean.class){
			obj=Boolean.valueOf(value);
		}
		return obj;
	}
	
	 public Class transform(String name){
		 Class c=null;
		 switch(name){
		 case "int":
			c=int.class;
			break;
		 case "long":
			 c=long.class;
			 break;
		 case "short":
			 c=short.class;
			 break;
		 case "float":
			 c=float.class;
			 break;
		 case "double":
			 c=double.class;
			 break;
		 case "boolean":
			 c=int.class;
			 break;
		 case "char":
			 c=char.class;
			 break;
		 case "byte":
			 c=byte.class;
			 break;
		default:
			try {
				c=Class.forName(name);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		 }
		 return c ;
	 }
}
