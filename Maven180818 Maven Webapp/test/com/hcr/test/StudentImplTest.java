package com.hcr.test;

import org.junit.Before;
import org.junit.Test;

import com.hcr.beans.Student;
import com.hcr.dao.IStudentDao;
import com.hcr.dao.impl.StudentDaoImpl;

public class StudentImplTest {
	IStudentDao dao;
	@Before
	public void init(){
	dao=new StudentDaoImpl();
	}
	
	@Test
	public void testInseartStu(){
		Student student=new Student("hcr", 20, 90);
		int insertStu = dao.insertStu(student);
		if(insertStu>0){
			System.out.println("²Ù×÷³É¹¦");
		}
		
		
		
	}

}
