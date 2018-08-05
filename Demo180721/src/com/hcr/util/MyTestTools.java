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
	 * �����ع�
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
		//��ȡ�����ļ���Key
		Set<String> set = pro.stringPropertyNames();
		set.forEach(key->{
			//��ȡkey��Ӧ��valueֵ
			String value = pro.getProperty(key);
			//����
			analysis(key, value);
		});
	}
	/**
	 * @descript ���������ļ��еĹ��캯���ͷ����Ĳ������ͺ�ֵ
	 * @param name �����ļ���key
	 * @param value �����ļ���value
	 */
	public void analysis(String name,String value){
		String className=null;
		 String consParamType="";
		 String consParamValue=null;
		 String methodName=null;
		 String methodParamType=null ;
		 String methodParamValue=null;
		//���������͹��캯������
		Pattern pattern = Pattern.compile("(.+)_\\((.*)\\)");
		//ƥ�������ļ���key��
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			//���飬�������
		 className = matcher.group(1);
		 //��ù��캯����������
		 consParamType = matcher.group(2);		 
		}
		//�������
		Pattern pattern2 = Pattern.compile("\\((.*)\\)_(.+)\\((.*)\\)_\\((.*)\\)");
		//ƥ�������ļ���value
		Matcher matcher2 = pattern2.matcher(value);
		if(matcher2.find()){
			//��ȡ���캯���������Ͷ�Ӧ��ֵ
			 consParamValue = matcher2.group(1);
			 //������
			 methodName = matcher2.group(2);
			 //��ȡ������������
			 methodParamType = matcher2.group(3);
			 //��ȡ����������Ӧ��ֵ
			 methodParamValue = matcher2.group(4);
		}
		
		 try {
			 //���������
			this.clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		    makeCons(consParamType);//��ȡ���캯����������
			this.makeConsObject(consParamValue);//��ȡ������Ӧ��ֵ
			this.makeTarget();//���󴴽�
			this.methodParams=this.getParamClasses(methodParamType);
			getMethod(methodName,methodParamType);//����Method
			makeMethodParams(methodParamValue);
			execute();//ִ��
			/*
			 * ִ����ߵķ�����
			 *    1��Method 2������ֵ���ɵ����� 
			 *  Method Class ��������  �����б�(Class...)
			 *  
			 *  ִ�У�
			 *    method  Ŀ�����  ����ֵ���󹹳ɵ�����
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
	 * ͨ������Ĳ������͵õ��������Ͷ�Ӧ ����
	 * @param paramType ��������
	 * @return �������Ͷ�Ӧ ����
	 */
	 public Class[] getParamClasses(String paramType){
		 Class[] classes=null;
		 //û�в�������
		 if(paramType==null||"".equals(paramType.trim())){
			 //�����޲ζ���
			 classes=new Class[]{};
		 }else{
			 //�вΣ���ȡ������������
			 String[] arr = paramType.split(",");
			classes= new Class[arr.length];
			for (int i = 0; i < arr.length; i++) {
				//����transform(��������)��������������ת�ɶ�Ӧ ����
				classes[i]=this.transform(arr[i]);
			}
		 }
		return classes;
	 }
	 
	 /**
	  * ͨ�������������ɺ�������
	  * @param paramType ��������
	  */
	 public void makeCons(String paramType){
		 //�޲�
		 if(paramType==null||"".equals(paramType.trim())){
			 //��ȡ�޲ι��캯��
			this.clazz.getConstructors();
		 }else{
			 //�вΣ���ȡ������������
			 String[] arr = paramType.split(",");
			this.consParams= new Class[arr.length];
			for (int i = 0; i < arr.length; i++) {
				this.consParams[i]=this.transform(arr[i]);
			}
			try {
				//��ȡ�вι��캯��
				this.cons=clazz.getConstructor(consParams);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 
	 /**
	  * 
	  * @param types �������Ͷ�Ӧ��ת����
	  * @param consString  �������Ͷ�Ӧ��ֵ
	  * @return ֵ
	  */
	 public Object[] getParamValues(Class[] types,String consString){
			Object[] paramValues=null;
			//��ֵ
			if(consString==null || "".equals(consString.trim())){
				paramValues=new Object[]{};
			}else{
				//��ֵ����ȡ�������͵�ֵ
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
	  * @param consString  ���캯���Ĳ�����Ӧ��ֵ
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
	  * ��÷�����ֵ
	  * @param consString �����Ĳ�����Ӧ��ֵ
	  */
	 public void makeMethodParams(String consString){
			this.methodValues=getParamValues(this.methodParams,consString);
		}

	 
	 public void makeTarget(){
		
				try {
					//��������
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
