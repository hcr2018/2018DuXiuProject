package com.hcr.xml;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Attr;
//对w3c dom支持
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.hcr.bean.BeanValue;
import com.hcr.bean.Student;
import com.hcr.transform.BeanValueToBean;
import com.hcr.transform.XMLToBeanValue;

public class XmlEx {
	
	@Test 
	public void testBean(){
		XMLToBeanValue xmlToBean = new XMLToBeanValue();
		BeanValueToBean bean = new BeanValueToBean();
		try {
			Student transform = bean.transform(xmlToBean.readXml("/student.xml"));
			System.out.println(transform);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBeanValue(){
		
		XMLToBeanValue xmlToBean = new XMLToBeanValue();
		try {
			BeanValue bean = xmlToBean.readXml("/student.xml");
			System.out.println(bean);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testTriValue(){
		String s1="name";
		String t1="java.lang.String";
		String v1="zhangsan";
		
		String s2="height";
		String t2="double";
		String v2="166";
		
		String s3="sex";
		String t3="boolean";
		String v3="true";
		
		
		Map<String,SimpleEntry<String,String>> map=new HashMap<>();
		map.put(s1, new SimpleEntry(t1,v1) );
		map.put(s2, new SimpleEntry(t2,v2) );
		map.put(s3, new SimpleEntry(t3,v3) );
		
		map.forEach((name,typeAndValue)->System.out.println(name+"\t"+typeAndValue.getKey()+"\t"+typeAndValue.getValue()));
	}
	
	
	
	@Test
	public void readXml2() throws Exception{
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//构建模式
		DocumentBuilder documentBuilder=factory.newDocumentBuilder();
		//Class里边的getResourceAsStream("/");如果以/开头，就到啊classPath中找这个文件
		//&nbsp; &gt;  &quot;
		Document document=null;
		try(
		InputStream is=XMLToBeanValue.class.getResourceAsStream("/student.xml");
		){
			document=documentBuilder.parse(is);
		}
		//<student>
		Element element=(Element) document.getChildNodes().item(0);
		System.out.println(element.getNodeName()+"\t"+element.getNodeValue());
		//System.out.println(document.getNodeValue());
		//document.getElementsByTagName("student")[0].getAttribute("class");
		//获取元素的属性值
		String className = element.getAttribute("class");
		System.out.println(className);
	
		
		//<name>
		NodeList nodeList=element.getElementsByTagName("name");
		Element name=(Element) nodeList.item(0);
		
		String attr=name.getAttribute("value");
		System.out.println(attr);
		
		String paramType = name.getAttribute("type");
		System.out.println(paramType);
		
		
		
		
		NodeList nodeListHgight=element.getElementsByTagName("height");
		Element height=(Element) nodeListHgight.item(0);
		
		
		Text heightText=(Text) height.getFirstChild();
		//String text=height.getTextContent();
		//.getWholeText()取出文本节点的所有文本内容
		System.out.println(heightText.getNodeValue().trim());
		String paramType2 = height.getAttribute("type");
		System.out.println(paramType2);
					
	
				
		
	}

	
}
