<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.MemberInformation" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'findMember.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
         <div align="center">
  <table width="536">
    <tr>
      <td colspan="4"><div align="center"><strong><font size="+3" color="#FFCCCC">会员管理系统</font> </strong></div></td>
    </tr>
    <tr>
      <td width="173"><div align="center"><a href="jsp/register.jsp">会员注册</a></div></td>
      <td width="119"><div align="center"><a href="jsp/login.jsp">会员登录</a></div></td>
      <td width="112"><div align="center"><a href="jsp/uploadPic.jsp">上传照片</a></div></td>
      <td width="112"><div align="center"><a href="jsp/lookMember.jsp">浏览会员</a></div></td>
    </tr>
    <tr>
      <td><div align="center"><a href="jsp/updateRegister.jsp">修改注册信息</a></div></td>
      <td><div align="center"><a href="jsp/updatePwd.jsp">修改密码</a></div></td>
      <td><div align="center"><a href="exit">退出登录</a></div></td>
      <td><div align="center"><a href="jsp/index.jsp">返回主页</a></div></td>
    </tr>
  </table>
</div>
<center>
<jsp:useBean id="memberInfo" class="tom.bean.MemberInformation" scope="request"></jsp:useBean>
<table border="1" width="800">
<tr><th>会员名</th><th>性别</th><th>年龄</th><th>电话</th><th>电子邮件</th><th>简历</th><th>照片</th></tr>
<tr>
<th><jsp:getProperty property="name" name="memberInfo"/></th>
<th><jsp:getProperty property="sex" name="memberInfo"/></th>
<th><jsp:getProperty property="age" name="memberInfo"/></th>
<th><jsp:getProperty property="phone" name="memberInfo"/></th>
<th><jsp:getProperty property="email" name="memberInfo"/></th>
<th><jsp:getProperty property="message" name="memberInfo"/></th>
<th><img src='image1/<jsp:getProperty property="pic" name="memberInfo"/>/<jsp:getProperty property="pic" name="memberInfo"/>' width="100" height="100"></th>
</tr>
</table>
</center>
  </body>
</html>
