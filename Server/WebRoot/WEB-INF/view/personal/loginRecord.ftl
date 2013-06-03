<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
table.gridtable th {
    border-width: 0px;
    padding: 8px;
    background-color: #F5F5F5;
}
table.gridtable td {
    border-width: 0px;
    padding: 4px;
    background-color: #ffffff;
    text-align: center;
}

.hr2{ height:2px;border:none;border-top:2px solid #C7C7C7;padding:0px;}
.hrLight {
	margin:0px;
	padding:0px;
	height: 1px;
	border: none;
	border-top: 1px solid #efefef;
}
</style>
</head>
<body>
	<div class="commonBody">
	<table class="gridtable">
		<tr>
		   <th>时间</th><th>IP</th><th>操作系统</th><th>浏览器</th>
		</tr>
		<#list cdrs?keys as key>
			<tr>
			 <td colspan="4" style="font-weight:bold;text-align: left;">${key}<hr class="hr2"/></td>
			</tr>
			<#assign loginCdrs = cdrs[key]/>
			
			<#list loginCdrs as cdr>
			<tr>
			   <td>${cdr.time}</td><td>${cdr.ip}</td><td>${cdr.os!""}</td><td>${cdr.browser!""}</td>
			</tr>
			<tr>
				 <td colspan="4"><hr class="hrLight"/></td>
			</tr>
			</#list>
		</#list>
	</table>
	</div>
</body>
</html>