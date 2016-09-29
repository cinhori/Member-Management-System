<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>会员上传图片页面</title>

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
	<object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="400" height="200">
  <param name="movie" value="flash/upload.swf" />
  <param name="quality" value="high" />
  <param name="wmode" value="opaque" />
  <param name="swfversion" value="6.0.65.0" />
  <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
  <param name="expressinstall" value="Scripts/expressInstall.swf" />
  <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
  <!--[if !IE]>-->
  <object type="application/x-shockwave-flash" data="flash/upload.swf" width="400" height="200">
    <!--<![endif]-->
    <param name="quality" value="high" />
    <param name="wmode" value="opaque" />
    <param name="swfversion" value="6.0.65.0" />
    <param name="expressinstall" value="Scripts/expressInstall.swf" />
    <!-- 浏览器将以下替代内容显示给使用 Flash Player 6.0 和更低版本的用户。 -->
    <div>
      <h4>此页面上的内容需要较新版本的 Adobe Flash Player。</h4>
      <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获取 Adobe Flash Player" /></a></p>
    </div>
    <!--[if !IE]>-->
  </object>
  <!--<![endif]-->
</object>
		<p>文件将要被上传到：D:\Java\apache-tomcat-8.0.33\webapps\MemberMngmentSystem\image中
		<p>选择要上传的图像照片文件(名字不可以含有非ASCII码字符，比如汉字等)：
		<form action="UploadPicture" method="post" enctype="multipart/form-data">
			<input type="file" name="fileName" size="40" /> <br />
			<input type="submit" value="上传" name="UploadPicture" />
		</form>
	</center>
</body>
</html>
