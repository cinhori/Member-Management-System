<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.UploadPic" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传图片成功后显示信息</title>
    
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
				<td colspan="4"><div align="center">
						<strong><font size="+3" color="#FFCCCC">会员管理系统</font> </strong>
					</div>
				</td>
			</tr>
			<tr>
				<td width="173"><div align="center">
						<a href="jsp/register.jsp">会员注册</a>
					</div>
				</td>
				<td width="119"><div align="center">
						<a href="jsp/login.jsp">会员登录</a>
					</div>
				</td>
				<td width="112"><div align="center">
						<a href="jsp/uploadPic.jsp">上传照片</a>
					</div>
				</td>
				<td width="112"><div align="center">
						<a href="jsp/lookMember.jsp">浏览会员</a>
					</div>
				</td>
			</tr>
			<tr>
				<td><div align="center">
						<a href="jsp/updateRegister.jsp">修改注册信息</a>
					</div>
				</td>
				<td><div align="center">
						<a href="jsp/updatePwd.jsp">修改密码</a>
					</div>
				</td>
				<td><div align="center">
						<a href="exit">退出登录</a>
					</div>
				</td>
				<td><div align="center">
						<a href="jsp/index.jsp">返回主页</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<center>
	<jsp:useBean id="upload" class="tom.bean.UploadPic" scope="request"></jsp:useBean>
	<br/><jsp:getProperty property="backNews" name="upload"/>
	<br/>上传的文件名字:<jsp:getProperty property="fileName" name="upload"/>
		保存后的文件名字:<jsp:getProperty property="savedFileName" name="upload"/>
		<br/>图像效果为:<img src="image/<jsp:getProperty name="upload" property="savedFileName"/>/<jsp:getProperty name="upload" property="savedFileName"/>" width="100" height="100"/>
	</center>
  </body>
</html>
