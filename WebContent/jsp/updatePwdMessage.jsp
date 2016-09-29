<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.UpdatePassword"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改密码后信息显示</title>

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
		<jsp:useBean id="password" class="tom.bean.UpdatePassword"
			scope="request"></jsp:useBean>
			<jsp:getProperty property="backNews" name="password"/>
			<br/>您的新密码为：<jsp:getProperty property="newPassword1" name="password"/>
			<br/>您的旧密码为:<jsp:getProperty property="password" name="password"/>
	</center>
</body>
</html>
