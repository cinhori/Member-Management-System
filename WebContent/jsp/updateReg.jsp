<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.Register"%>
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

<title>修改注册信息</title>

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
		<jsp:useBean id="register" class="tom.bean.Register" scope="request"></jsp:useBean>
		<br />以下是<jsp:getProperty property="name" name="register" />会员曾经注册的信息，您可以修改这些信息。
		<form action="UpdateRegisterMessage" method="post">
		<table>
		<tr><td>性别:<jsp:getProperty property="sex" name="register"/></td><td><input type="radio" value="男" name="newSex" checked="checked"/>男<input type="radio" value="女" name="newSex"/>女</td></tr>
		<tr><td>会员年龄:</td><td><input type="text" name="newAge" value='<jsp:getProperty property="age" name="register"/>'/></td></tr>
		<tr><td>电子邮件:</td><td><input type="text" name="newEmail" value='<jsp:getProperty property="email" name="register"/>'/></td></tr>
		<tr><td>联系电话:</td><td><input type="text" name="newPhone" value='<jsp:getProperty property="phone" name="register"/>'/></td></tr>
		<tr><td>输入您的个人简介:</td></tr>
		<tr><td><textarea rows="6" cols="30" name="newMessage"><jsp:getProperty property="message" name="register"/></textarea></td></tr>
		<tr><td><input type="submit" value="提交修改"/>&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</table>
		</form>
	</center>
</body>
</html>
