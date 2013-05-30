<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">

</style>
</head>
<body>
	<div class="commonBody">
		<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>新增角色</span> </a>
		<hr/>
		<div style="height:10px;"></div>
		<table class="gridtable">
			<tr>
			    <th>ID</th><th>角色名称</th><th>备注</th><th>状态</th><th>创建时间</th><th>操作</th>
			</tr>
			<#if roles?exists>
			<#list roles as role>
			<tr>
				<#assign statusStr="有效" />
				<#if role.status!=0>
				<#assign statusStr="无效" />
				</#if>
			    <td>${role.id}</td><td <#if role.status!=0>style="color:red;"</#if>>${role.name}</td><td>${role.desc}</td><td>${statusStr}</td><td>${role.createDate}</td><td><img src="images/detail.png" style="margin-right:20px;"/><img src="images/delete.png" style="margin-right:20px;"/><img src="images/limit.png"/></td>
			</tr>
			</#list>
			</#if>
		</table>
		<div style="margin-top:5px;margin-bottom:5px;">注：红色代表角色无效，该角色下的操作员都不能登录</div>
	</div>
</body>
</html>