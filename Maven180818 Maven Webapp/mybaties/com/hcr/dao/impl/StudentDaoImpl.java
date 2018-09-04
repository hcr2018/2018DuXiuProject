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
			/*//1.读取配置文件
			InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
			//2.创建sqlsessonfactory对象
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//3.创建
			session = sessionFactory.openSession();
			*/
			
			//获取sqlsession对象
			session=MybatisUtil.getSqlSession();
			//4.操作
			session.insert("insertStu", student);
			//5.sqlsession提交
			session.commit();		
		}finally {
			//6.关闭sqlsession
			if(session!=null){
			session.close();
			}
		}
		return 0;
	}

}
