package com.hcr.dao.impl;



import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hcr.beans.Student;
import com.hcr.dao.IStudentDao;
import com.hcr.utils.MybatisUtil;

public class StudentDaoImpl implements IStudentDao {
	private SqlSession session;
	public int insertStu(Student student) {
		try {
			/*//1.��ȡ�����ļ�
			InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
			//2.����sqlsessonfactory����
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//3.����
			session = sessionFactory.openSession();
			*/
			
			//��ȡsqlsession����
			session=MybatisUtil.getSqlSession();
			//4.����
			session.insert("insertStu", student);
			//5.sqlsession�ύ
			session.commit();		
		}finally {
			//6.�ر�sqlsession
			if(session!=null){
			session.close();
			}
		}
		return 0;
	}

}
