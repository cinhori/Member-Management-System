<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员注册页面</title>
    
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
  <form action="RegisterMessage" method="post">
    <br/>输入你的信息，会员名字必须由字母和数字组成，带*号项必须填写
    <table>
	<tr><td>会员名称：</td><td><input type="text"  name="name"/>*</td></tr>
    <tr><td>设置密码：</td><td><input type="password"  name="password"/>*</td></tr>
    <tr><td>确认密码：</td><td><input type="password"  name="password1"/>*</td></tr>
    <tr><td>性别：</td><td><input name="sex" type="radio" checked="checked" align="middle" value="男"/>男
     <input type="radio" name="sex" align="middle" value="女"/>女&nbsp; &nbsp;* </td></tr>
    <tr><td>会员年龄：</td><td><input type="text"  name="age"/>*</td></tr>
    <tr><td>电子邮件：</td><td><input type="text"  name="email"/></td></tr>
    <tr><td>联系电话：</td><td><input type="text"  name="phone"/></tr></td>
    </table>
    <table>
    <tr><td>输入你的个人简历：</td></tr>
    <tr><td><textarea name="message" rows="6" cols="30"></textarea></td></tr>
    <tr><td><input type="submit" name="submit" value="提交"/></td></tr>
    </table>
        <object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="400" height="200">
        <param name="movie" value="flash/register.swf" />
        <param name="quality" value="high" />
        <param name="wmode" value="opaque" />
        <param name="swfversion" value="6.0.65.0" />
        <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
        <param name="expressinstall" value="Scripts/expressInstall.swf" />
        <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="flash/register.swf" width="400" height="200">
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
  </form>
</center>
  </body>
</html>
