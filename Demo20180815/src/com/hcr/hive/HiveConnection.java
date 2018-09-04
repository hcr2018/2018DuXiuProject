package com.hcr.hive;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveConnection {

	protected static Statement stmt;
	protected static Connection con;
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	 public static void main(String[] args) throws SQLException{
      
            try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            //连接hiveserver2

            con = DriverManager.getConnection("jdbc:hive2://hadoop1:10000/hive_1","root","root");
            stmt = con.createStatement();
            String sql="select sno,sname from student";
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
            	System.out.println(res.getString(1)+"  "+res.getString(2));
            }
            
            DatabaseMetaData metaData = con.getMetaData();//获取元数据
            ResultSet schemas = metaData.getSchemas();
            while(schemas.next()){
            	
            }
	 	
    
	 	}
	}