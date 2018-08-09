package com.hcr.wangpan.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;



public class HdfsUtil {
	
	private static FileSystem fs;
	
	static{
		//…˘√˜≈‰÷√
	Configuration conf = new Configuration();
	conf.set("fs.defaultFS","hdfs://192.168.145.130:8020");
	try {
		fs=FileSystem.get(new URI("hdfs://192.168.145.130:8020/"),conf);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void fileUpload(String path,String hdfsPath ){
		try {
			fs.copyFromLocalFile(new Path(path), new Path("/"+hdfsPath));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
