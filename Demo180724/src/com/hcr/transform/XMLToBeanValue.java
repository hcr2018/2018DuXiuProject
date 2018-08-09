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
 * 将XML文件解析出来通过反射映射到bean类
 * @author cjx
 * @version 2018-07-24 v1.0
 *
 */

public class XMLToBeanValue {
	
	/**
	 * 读取XML文件中的元素属性值
	 * @param fileName
	 * @return 返回封装到一个beanvalue类中
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public BeanValue readXml(String fileName) throws ParserConfigurationException, SAXException, IOException{
		BeanValue bean=new BeanValue();
		Document document = init(fileName);
		Element element=(Element) document.getChildNodes().item(0);
		//获取类名
		String className=element.getAttribute("class");
		System.out.println(className);
		//封装到beanvalue中
		bean.setClassName(className);
		bean.setPropertyValues(getFiled(element));
		return bean;
	}






	/**
	 * 获取元素节点名、value、type方法
	 * @param element
	 * @return
	 */
	private Map<String,SimpleEntry<String,String>> getFiled(Element element) {
		Map<String,SimpleEntry<String,String>> map=new HashMap<>();
		//获取元素名
		NodeList nodeList = element.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			//只取出属于ELEMENT_NODE类型的节点名
			if(item.getNodeType()==Node.ELEMENT_NODE){
				//节点名
				String strName = item.getNodeName();
				System.out.println(strName);
				Element name=(Element) item;
				//得到value标签或属性对应的值
				String sValue = getValue(name,"value");
				System.out.println(sValue);
				//得到type标签或属性对应的值
				String sType = getValue(name,"type");
				System.out.println(sType);
				//封装到beanvalue中
				map.put(strName, new SimpleEntry<String, String>(sValue, sType));
			}
			
		}
		return map;
	}






	/**
	 * 获取 属性名或子节点名下对应的值
	 * @param name  属性名或子节点名
	 * @param value  节点元素名
	 * @return
	 */
	private String getValue(Element name,String value) {
		//节点名对应的值
		String sValue = name.getAttribute(value);
       //	System.out.println(paramValue);
		//如果属性不存在，找子节点
		if(sValue==null || sValue.equals("") ){
			Element valueElm=(Element) name.getElementsByTagName(value).item(0);
			//子节点对应的文本值
			sValue=valueElm.getTextContent();
			//System.out.println("44444:::"+paramValue);
		}
		return sValue;
	}
	
	/**
	 * 读取文件
	 * @param fileName
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Document init(String fileName) throws ParserConfigurationException, SAXException, IOException {
		//工厂模式
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//构建模式
		DocumentBuilder documentBuilder=factory.newDocumentBuilder();
		//Class里边的getResourceAsStream("/");如果以/开头，就到啊classPath中找这个文件
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
	 * @param cladd 数据类型转化的类
	 * @param value 参数对应的值
	 * @return 返回一个对象
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
	 * 数据类
	 * @param name
	 * @return 返回数据类型转换成的类
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
				//String类型直接用
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
