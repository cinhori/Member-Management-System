<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("path: " + path + " \nbasePath: " + basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员管理系统主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<script type="text/javascript">
function exit(){
	window.alert("退出？");
	window.close();
}
</script>
  </head>
  
  <body>
      <center>
  <div align="center">
  <bgsound loop="infinite" src="music/main.mp3"/>
    <hr align="center" color="#66FF66"/>
<br/>
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
    <td><div align="center"><input type="button" onclick="window.close()" value="退出"> </div></td>
    <td><div align="center"><a href="jsp/index.jsp">返回主页</a></div></td>
  </tr>
  <tr>
    <td colspan="4"><div align="center"><strong><font size="+2" color="#66CCCC">欢迎您的到来!</font></strong></div></td>
  </tr>
  <tr>
    <td colspan="4"><div align="center">
 <object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="400" height="200">
  <param name="movie" value="flash/index.swf" />
  <param name="quality" value="high" />
  <param name="wmode" value="opaque" />
  <param name="swfversion" value="6.0.65.0" />
  <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
  <param name="expressinstall" value="Scripts/expressInstall.swf" />
  <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
  <!--[if !IE]>-->
  <object type="application/x-shockwave-flash" data="flash/index.swf" width="400" height="200">
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
    </div></td>
  </tr>
</table>
</div>
</center>
  </body>
  
  </html>
