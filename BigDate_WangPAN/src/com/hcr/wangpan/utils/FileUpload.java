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
		//��ȡ�ļ��ϴ�Ŀ¼
		String realPath = request.getRealPath("/");
		
		//��ȡ�ļ����浽��������Ŀ¼
		String dirPath=realPath+"/upload";
		File file = new File(dirPath);
		
		//�жϱ����·���Ƿ����
		if(!file.exists() &&!file.isDirectory()){
			file.mkdirs();
		}
		 //��Ϣ��ʾ
		String message = "";
		//ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
		//1������һ��DiskFileItemFactory����
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		//2�������ļ��ϴ�������
		ServletFileUpload fileUpload = new ServletFileUpload(dfif);
		  //����ϴ��ļ�������������
		fileUpload.setHeaderEncoding("UTF-8"); 
		//3���ж��ύ�����������Ƿ����ϴ���������
		if(!ServletFileUpload.isMultipartContent(request)){
			//���մ�ͳ��ʽ��ȡ����
			return map;
		}
		//4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
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
				//���fileitem�з�װ������ͨ�����������
				String fileName=null;
				if(item.isFormField()){
					 fileName = item.getFieldName();
					 //�����ͨ����������ݵ�������������
					try {
						String value = item.getString("UTF-8");
						
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}else{
					//���fileitem�з�װ�����ϴ��ļ�
					//�õ��ϴ����ļ����ƣ�
					String name = item.getName();
					if(name==null || name.trim().equals("")){
						 continue;
					}
						//ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
						//�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
					 fileName = name.substring(name.lastIndexOf("\\")+1);
					//���ļ���ת���ɱ����ļ�
					 File saveFile = new File(dirPath,fileName);
					 //д����������
					 try {
						item.write(saveFile);
						 message = "�ļ��ϴ��ɹ���";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						 message= "�ļ��ϴ�ʧ�ܣ�";
						e.printStackTrace();
					}
					 
					//�洢��map������
					 map.put("name", item.getName());
					 map.put("newName", fileName);
					 map.put("size", item.getSize());
					 map.put("url", "upload/"+fileName);
				}
				 request.setAttribute("message",message);	
			}
			//ͬ�����ֲ�ʽ�ļ�ϵͳ��
			HdfsUtil hdfsUtil = new HdfsUtil();
			hdfsUtil.fileUpload(dirPath, "hcr_file");
		}
		return map;
	}
	
	
	public static void main(String[] args) {
		
		//1.�ļ��ϴ�
		//2.�ϴ������ط�����
		//3.�ϴ���hdfs
	}
	
}
