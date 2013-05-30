<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
.serverBaseItem{
	margin-bottom:10px;
}
.serverTitle{
	font-weight:bold;margin-top:20px;font-size:13px;
}
</style>
</head>
<body>
	<div class="commonBody">
		<div style="font-weight:bold;font-size:13px;">WEB服务器信息</div>
		<hr/>
		<div class="serverBaseItem">服务器启动时间：${startTime}</div>
		<div class="serverBaseItem">服务器运行时间：${seconde}</div>
		<div class="serverBaseItem">软件版本信息：${systemVersion}</div>
		<div class="serverBaseItem">内存信息：${memoryInfo!""}</div>
		<div class="serverBaseItem">OS/硬件：${osInfo}</div>
		<div class="serverBaseItem">JVM 版本和供应商：${jvmInfo}</div>
		<div class="serverBaseItem">软件容器： ${appServer}</div>
		<div class="serverBaseItem">软件目录： ${appRealPath!""}</div>
		<div class="serverBaseItem">访问路径：<#if ip?exists>${ip}:</#if>80</div>
		
		<div class="serverTitle">SOCKET服务器信息</div>
		<hr/>
		<div class="serverBaseItem">访问路径：<#if ip?exists>${ip}:</#if>5222</div>
		<div class="serverBaseItem">SSL访问路径：暂未支持</div>
		
	</div>
</body>
</html>