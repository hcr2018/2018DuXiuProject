package com.hcr.wangpan.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public  class FileUpload {
	
	public HashMap<String, Object>  uploadUtil(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		//获取文件上传目录
		String realPath = request.getRealPath("/");
		
		//获取文件保存到服务器的目录
		String dirPath=realPath+"/upload";
		File file = new File(dirPath);
		
		//判断保存的路径是否存在
		if(!file.exists() &&!file.isDirectory()){
			file.mkdirs();
		}
		 //消息提示
		String message = "";
		//使用Apache文件上传组件处理文件上传步骤：
		//1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		//2、创建文件上传解析器
		ServletFileUpload fileUpload = new ServletFileUpload(dfif);
		  //解决上传文件名的中文乱码
		fileUpload.setHeaderEncoding("UTF-8"); 
		//3、判断提交上来的数据是否是上传表单的数据
		if(!ServletFileUpload.isMultipartContent(request)){
			//按照传统方式获取数据
			return map;
		}
		//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		List list = null;
		try {
			list = fileUpload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(null!=list){
			Iterator it = list.iterator();
			while(it.hasNext()){
				FileItem item = (FileItem) it.next();
				//如果fileitem中封装的是普通输入项的数据
				String fileName=null;
				if(item.isFormField()){
					 fileName = item.getFieldName();
					 //解决普通输入项的数据的中文乱码问题
					try {
						String value = item.getString("UTF-8");
						
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}else{
					//如果fileitem中封装的是上传文件
					//得到上传的文件名称，
					String name = item.getName();
					if(name==null || name.trim().equals("")){
						 continue;
					}
						//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					 fileName = name.substring(name.lastIndexOf("\\")+1);
					//将文件流转换成本地文件
					 File saveFile = new File(dirPath,fileName);
					 //写到服务器上
					 try {
						item.write(saveFile);
						 message = "文件上传成功！";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						 message= "文件上传失败！";
						e.printStackTrace();
					}
					 
					//存储到map集合中
					 map.put("name", item.getName());
					 map.put("newName", fileName);
					 map.put("size", item.getSize());
					 map.put("url", "upload/"+fileName);
				}
				 request.setAttribute("message",message);	
			}
			//同步到分布式文件系统中
			HdfsUtil hdfsUtil = new HdfsUtil();
			hdfsUtil.fileUpload(dirPath, "hcr_file");
		}
		return map;
	}
	
	
	public static void main(String[] args) {
		
		//1.文件上传
		//2.上传到本地服务器
		//3.上传到hdfs
	}
	
}
