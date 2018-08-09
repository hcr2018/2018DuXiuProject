package com.hcr.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ProxyFactory;
import org.apache.commons.dbutils.wrappers.SqlNullCheckedResultSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class JDBC {
	private Connection conn;
	private BasicDataSource bds;

	@Before
	public void init() {
		String url = "jdbc:mysql://localhost:3306/publisher?useSSL=false&useUnicode=true&charatterEncoding=utf-8";
		String passwd = "root";
		String user = "root";
		String driver = "com.mysql.jdbc.Driver";
		 bds= new BasicDataSource();

		bds.setUrl(url);
		bds.setDriverClassName(driver);
		bds.setUsername(user);
		bds.setPassword(passwd);

		try {
			conn = bds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { Class.forName(driver); } catch (ClassNotFoundException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * try { con = DriverManager.getConnection(url, user, password); } catch
		 * (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

	}

	
	@Test
	public void testPreparedStatement() {
		String sql = "select publisherId id,name,address from publisher";
		try (

				PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");

				System.out.println(id + "\t" + name + "\t" + address);
				if (name.contains("���")) {
					name = name.replace("���", "����");
					rs.updateString("name", name);
				} else if (name.contains("�鴫��")) {
					name = name.replace("�鴫��", "���ٴ�");
					rs.updateString("name", name);
				}
				rs.updateRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �Ż�����������һ��ֵ
	 */
	@Test
	public void testPreparedStatement2() {
		String sql = "select publisherId id,name,address from publisher where publisherId>?";
		try (

				PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_UPDATABLE);){
			     pstmt.setInt(1, 3);
			try(	ResultSet rs = pstmt.executeQuery();){
				
				
				SqlNullCheckedResultSet ncrs = new SqlNullCheckedResultSet(rs);
				ncrs.setNullString("*****");
				ncrs.setNullInt(-999);
				ResultSet crs = ProxyFactory.instance().createResultSet(ncrs);
				List<Publisher> list = new ArrayList<>();
				
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
				
				
				list.add(new Publisher(id, name, address));
				System.out.println(id + "\t" + name + "\t" + address);
				if (name.contains("���")) {
					name = name.replace("���", "����");
					rs.updateString("name", name);
				} else if (name.contains("�鴫��")) {
					name = name.replace("�鴫��", "���ٴ�");
					rs.updateString("name", name);
				}
				rs.updateRow();
			}
				list.forEach(System.out::println);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * spring jdbcTemplate
	 * BasicRowProcessor()
	 */
	@Test
	public void testJdbcTemplate(){
		
		JdbcTemplate template = new JdbcTemplate(bds);
		
		//��ֻ��һ����������ʹ��lamda���ʽ
		PreparedStatementCreator psc=(con)->{
			return con.prepareStatement("select publisherId id,name,address from publisher where publisherId>? and 6=?");
			
		};
		
		PreparedStatementSetter pss=(pstmt)->{
			pstmt.setInt(1, 3);
			pstmt.setInt(2, 6);
		};
		
		 ResultSetExtractor<List<Publisher>> rse=(rs)->{
			 
			 return new BasicRowProcessor().toBeanList(rs, Publisher.class);
			
		 };

		List<Publisher> query = template.query(psc,pss, rse);
		query.forEach(System.out::println);
		
	}
	
	/*
	 * rowMapper
	 */
	@Test
	public void testQuery(){
		
		JdbcTemplate template = new JdbcTemplate(bds);
		PreparedStatementCreator psc=(con)->{
			 PreparedStatement pstmt = con.prepareStatement("select publisherId id,name,address from publisher where publisherId>? and 6=?");
			 pstmt.setInt(1, 3);
			 pstmt.setInt(2, 6);
			return pstmt;
			 
			
		};
		
		//rowMapper---->mapRow(ResultSet rs, int rowNum)
		//Implementations must implement this method to map each row of data in the ResultSet.
		RowMapper<Publisher> rowMapper=(rs,rowNum)->{

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
			
			return new Publisher(id, name, address);
			

		};
		
		template.query(psc, rowMapper).forEach(System.out::println);
		
		
	}
	
	/**
	 * object
	 * MappingSqlQuery
	 */
	@Test
	public void testMappingSqlQuery(){
		
		MappingSqlQuery<Publisher> query = new MappingSqlQuery<Publisher>(bds,"select publisherId id,name,address from publisher where publisherId>? and 6=?") {

			@Override
			protected Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				
				return new Publisher(id, name, address);
				
			}
		}; 
		//�����������
		query.declareParameter(new SqlParameter(Types.INTEGER));
		query.declareParameter(new SqlParameter(Types.INTEGER));
		//�����ñ���Ҳ�����������
		query.compile();
		query.execute(3,6).forEach(System.out::println);
		
	}
	
	
	/**
	 * ����Ҫôȫ����Ҫôȫ����
	 */
	@Test
	public void testTransation(){
		 try {
			conn = this.bds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 String sql1="update publisher set name='����������' where publisherId=3";
		 String sql2="update publisher set name='*8*������' where publisherId=8";
		 PreparedStatement ps1 = null ;
		 try {
			 //Ԥ�������
			ps1= conn.prepareStatement(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			ps1.executeUpdate();
			 ps1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			ps1= conn.prepareStatement(sql2);
			ps1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				//ִ�д��󣬻ع�
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		try {
			//�ύ����
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		 
		 
	}
	

	@After
	public void destory() {
		try {
			DbUtils.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*if (this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}
