<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
.nodeItem{
	margin-top:5px;
}
.subNodeDetailItem{
	margin-left:20px;
}
</style>
</head>
<body>
	<div class="commonBody">
	<#if datas?exists>
		<#list datas as data>
		${data!""}
		</#list>
	</#if>
	</div>
</body>
</html>