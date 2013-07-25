<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
function kick(id){
	var uri = "kick.do";
	$.post(uri, {
		id : id
	}, function(data) {
		var resultCode = $(data).find("resultCode").text();
		if("0"==resultCode){
			$('#'+id).remove();
		}else{
			var resultMsg = $(data).find("resultMsg").text();
			alert(resultMsg);
		}
	});
}
</script>
</head>
<body>
	<div class="commonBody">
		<table class="gridtable">
			<tr>
			    <th>用户名称</th><th>IP</th><th>在线时间</th><th>创建时间</th><th>最后访问时间</th><th>操作</th>
			</tr>
			<#list sessions as session>
			<#if session.getAttribute("remote_addr")?exists>
				<#assign remoteIp = session.getAttribute("remote_addr")/>
			<#else>
				<#assign remoteIp = ""/>
			</#if>
			<#if session.getAttribute("operator")?exists>
				<#assign userName = session.getAttribute("operator").name/>
			<#else>
				<#assign userName = "游客"/>
			</#if>
			<tr id="${session.getId()}">
			    <td>${userName!"游客"}</td><td>${remoteIp!""}</td><td>${dateUtil.formatMs2String(nowTimeLong-session.getCreationTime())}</td><td>${dateUtil.formatLong2String(session.getCreationTime())}</td><td>${dateUtil.formatLong2String(session.getLastAccessedTime())}</td><td><a href="#" onClick="kick('${session.getId()}');">下线</a></td>
			</tr>
			</#list>
		</table>
	</div>
</body>
</html>