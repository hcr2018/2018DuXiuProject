package com.hcr.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	/**
	 * SqlSessionFactory ��Ϊ������������Ϊ�̰߳�ȫ�ģ����Զ���Ϊ����
	 */
	private static SqlSessionFactory factory ;
	public static SqlSession getSqlSession(){
				
		try {
			if(factory==null){
			//1.��ȡ�����ļ�
			InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
			//2.����sqlsessonfactory����
			factory= new SqlSessionFactoryBuilder().build(inputStream);
			}
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factory.openSession();
	}
}
