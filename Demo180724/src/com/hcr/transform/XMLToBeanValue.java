package com.hcr.transform;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hcr.bean.BeanValue;

/**
 * ��XML�ļ���������ͨ������ӳ�䵽bean��
 * @author cjx
 * @version 2018-07-24 v1.0
 *
 */

public class XMLToBeanValue {
	
	/**
	 * ��ȡXML�ļ��е�Ԫ������ֵ
	 * @param fileName
	 * @return ���ط�װ��һ��beanvalue����
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public BeanValue readXml(String fileName) throws ParserConfigurationException, SAXException, IOException{
		BeanValue bean=new BeanValue();
		Document document = init(fileName);
		Element element=(Element) document.getChildNodes().item(0);
		//��ȡ����
		String className=element.getAttribute("class");
		System.out.println(className);
		//��װ��beanvalue��
		bean.setClassName(className);
		bean.setPropertyValues(getFiled(element));
		return bean;
	}






	/**
	 * ��ȡԪ�ؽڵ�����value��type����
	 * @param element
	 * @return
	 */
	private Map<String,SimpleEntry<String,String>> getFiled(Element element) {
		Map<String,SimpleEntry<String,String>> map=new HashMap<>();
		//��ȡԪ����
		NodeList nodeList = element.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			//ֻȡ������ELEMENT_NODE���͵Ľڵ���
			if(item.getNodeType()==Node.ELEMENT_NODE){
				//�ڵ���
				String strName = item.getNodeName();
				System.out.println(strName);
				Element name=(Element) item;
				//�õ�value��ǩ�����Զ�Ӧ��ֵ
				String sValue = getValue(name,"value");
				System.out.println(sValue);
				//�õ�type��ǩ�����Զ�Ӧ��ֵ
				String sType = getValue(name,"type");
				System.out.println(sType);
				//��װ��beanvalue��
				map.put(strName, new SimpleEntry<String, String>(sValue, sType));
			}
			
		}
		return map;
	}






	/**
	 * ��ȡ ���������ӽڵ����¶�Ӧ��ֵ
	 * @param name  ���������ӽڵ���
	 * @param value  �ڵ�Ԫ����
	 * @return
	 */
	private String getValue(Element name,String value) {
		//�ڵ�����Ӧ��ֵ
		String sValue = name.getAttribute(value);
       //	System.out.println(paramValue);
		//������Բ����ڣ����ӽڵ�
		if(sValue==null || sValue.equals("") ){
			Element valueElm=(Element) name.getElementsByTagName(value).item(0);
			//�ӽڵ��Ӧ���ı�ֵ
			sValue=valueElm.getTextContent();
			//System.out.println("44444:::"+paramValue);
		}
		return sValue;
	}
	
	/**
	 * ��ȡ�ļ�
	 * @param fileName
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Document init(String fileName) throws ParserConfigurationException, SAXException, IOException {
		//����ģʽ
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//����ģʽ
		DocumentBuilder documentBuilder=factory.newDocumentBuilder();
		//Class��ߵ�getResourceAsStream("/");�����/��ͷ���͵���classPath��������ļ�
		//&nbsp; &gt;  &quot;
		Document document=null;
		try(
		InputStream is=XMLToBeanValue.class.getResourceAsStream(fileName);
		){
			document=documentBuilder.parse(is);
		}
		
		return document;
	}
	
	/**
	 * 
	 * @param cladd ��������ת������
	 * @param value ������Ӧ��ֵ
	 * @return ����һ������
	 */
	public static Object transfToObject(Class cladd,String value){
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
	
	/**
	 * ������
	 * @param name
	 * @return ������������ת���ɵ���
	 */
	 public static Class strToClass(String name){
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
			 c=boolean.class;
			 break;
		 case "char":
			 c=char.class;
			 break;
		 case "byte":
			 c=byte.class;
			 break;
		default:
			try {
				//String����ֱ����
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
