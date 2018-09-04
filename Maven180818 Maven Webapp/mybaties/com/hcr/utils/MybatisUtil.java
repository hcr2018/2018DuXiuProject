package com.hcr.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	/**
	 * SqlSessionFactory 类为重量级对象，且为线程安全的，所以定义为单例
	 */
	private static SqlSessionFactory factory ;
	public static SqlSession getSqlSession(){
				
		try {
			if(factory==null){
			//1.读取配置文件
			InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
			//2.创建sqlsessonfactory对象
			factory= new SqlSessionFactoryBuilder().build(inputStream);
			}
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factory.openSession();
	}
}
