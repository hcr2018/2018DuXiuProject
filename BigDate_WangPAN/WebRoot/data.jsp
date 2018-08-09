<%@page import="com.hcr.wangpan.utils.FileUpload"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	//HashMap<String,Object> map =FileUpload.uploadUtil(request,response);
	//out.print(Json);
    FileUpload fi= new FileUpload();
   HashMap<String,Object> map= fi.uploadUtil(request, response);
  
%>

