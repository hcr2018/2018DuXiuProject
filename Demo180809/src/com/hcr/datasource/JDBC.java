package com.hcr.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JDBC {
	private Connection conn;

	@Before
	public void init() {
		String url = "jdbc:mysql://localhost:3306/test";
		String passwd = "123456";
		String user = "root";
		String driver = "com.mysql.jdbc.Driver";
		BasicDataSource bds = new BasicDataSource();

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
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String address = rs.getString("address");

				System.out.println(id + "\t" + name + "\t" + address);
				if (name.contains("王睿")) {
					name = name.replace("王睿", "王锐");
					rs.updateString("name", name);
				} else if (name.contains("洪传荣")) {
					name = name.replace("洪传荣", "洪荣传");
					rs.updateString("name", name);
				}
				rs.updateRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void destory() {
		if (this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
