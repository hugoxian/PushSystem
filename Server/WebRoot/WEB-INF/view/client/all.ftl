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
			    <th>设备ID</th><th>UA</th><th>经纬度</th><th>创建时间</th><th>最后访问时间</th><th>状态</th><th>操作</th>
			</tr>
			<#if clients?exists&&(clients?size>0)>
				<#list clients as client>
				<tr>
				    <td>${client.deviceId!""}</td><td>${client.userAgent!""}</td><td>${client.lastLatLon!""}</td><td>${dateUtil.formatDate2String2(client.getCreateDate())}</td><td>${dateUtil.formatDate2String2(client.getLastAccessTime())}</td><td>正常</td><td><a href="#">下线</a></td>
				</tr>
				</#list>
			<#else>
			<tr>
			    <td colspan="7">This App has No Client</td>
			</tr>
			</#if>
		</table>
	</div>
</body>
</html>