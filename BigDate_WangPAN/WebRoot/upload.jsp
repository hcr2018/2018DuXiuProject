<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>文件上传</title>
  </head>
  <%
  String pic_path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
	   System.out.println("110110110:;:::;"+pic_path); %>
  <body>
    <form action="${pageContext.request.contextPath}/UploadUtil" enctype="multipart/form-data" method="post">
        上传用户：   <input type="text" name="username"><br/>
        上传文件1：<input type="file" name="file1"><br/>
        上传文件2：<input type="file" name="file2"><br/>
        <input type="submit" value="提交">
    </form>
  </body>
</html>
