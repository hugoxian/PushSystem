<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">

</style>
</head>
<body>
	<div class="commonBody">
		<table class="gridtable">
			<tr>
			    <th>设备ID</th><th>IP地址</th><th>在线时间</th><th>最后访问时间</th><th>操作</th>
			</tr>
			<#if sessions?exists&&(sessions?size>0)>
				<#list sessions?keys as key>
				<#assign session=sessions[key] />
				<#if session.getAttribute("DEVICE_ID")?exists>
					<#assign userName = session.getAttribute("DEVICE_ID")/>
				<#else>
					<#assign userName = "未知"/>
				</#if>
				<#if session.getAttribute("client_remote_addr")?exists>
					<#assign ip = session.getAttribute("client_remote_addr")/>
				<#else>
					<#assign ip = "未知"/>
				</#if>
				<tr>
				    <td>${userName!"游客"}</td><td>${ip}</td><td>${dateUtil.formatMs2String(nowTimeLong-session.getCreationTime())}</td><td>${dateUtil.formatLong2String(session.getLastReadTime())}</td><td><a href="#">下线</a></td>
				</tr>
				</#list>
			<#else>
			<tr>
			    <td colspan="7">No Client Connected</td>
			</tr>
			</#if>
		</table>
	</div>
</body>
</html>