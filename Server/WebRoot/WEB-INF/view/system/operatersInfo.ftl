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
		<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>新增操作员</span> </a>
		<hr/>
		<div style="height:10px;"></div>
		<table class="gridtable">
			<tr>
			    <th>ID</th><th>名称</th><th>角色</th><th>状态</th><th>在线状态</th><th>最近一次登录时间</th><th>创建时间</th><th>操作</th>
			</tr>
			<#if operators?exists>
			<#list operators as operator>
				<#assign roleName = "未知"/>
				<#assign roleStatus= 0/>
				<#if operator.role?exists>
				<#assign roleName=operator.role.name />
				<#assign roleStatus= operator.role.status/>
				</#if>
				
				<#assign statusStr="有效" />
				<#if operator.status!=0>
				<#assign statusStr="无效" />
				</#if>
				
				<#assign imgName="user-offline.png" />
				<#if operator.isOnline()>
				<#assign imgName="user-online.png" />
				</#if>
				
			<tr>
			    <td>${operator.id}</td><td <#if operator.status!=0>style="color:red;"</#if>>${operator.name}</td><td <#if roleStatus!=0>style="color:red;"</#if>>${roleName}</td><td>${statusStr}</td><td><img src="images/${imgName}"/></td><td><#if operator.lastLoginDate?exists>${operator.lastLoginDate?datetime}<#else>尚未登录过</#if></td><td>${operator.createDate}</td><td><img src="images/detail.png" style="margin-right:20px;"/><img src="images/delete.png" style="margin-right:20px;"/><img src="images/limit.png"/></td>
			</tr>
			</#list>
			</#if>
		</table>
		<div style="margin-top:5px;margin-bottom:5px;">注：红色代表操作员帐号、所属角色无效，不能登录</div>
	</div>
</body>
</html>