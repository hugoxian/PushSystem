<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
.profileItem{
	margin-bottom:10px;
}
</style>
</head>
<body>
	<div class="commonBody">
		<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>编辑个人资料</span> </a>
		<hr/>
		<div style="height:10px;"></div>
		<div class="profileItem">帐  号：${operator.account} / ${operator.name}</div>
		<div class="profileItem">角  色：${operator.role.name}</div>
		<div class="profileItem">手  机：${operator.phone!""}</div>
		<div class="profileItem">邮  箱：${operator.email!""}</div>
		<#if (operator.loginAtTheSameTime>1)>
		<div class="profileItem"><span style="color:red;">帐号[${operator.loginAtTheSameTime}]处登录，如非本人授权操作，请及时联系管理员</span></div>
		</#if>
		<hr/>
		<div style="height:10px;"></div>
		<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>新增程序</span> </a>
		<hr/>
		<div style="height:10px;"></div>
		<table class="gridtable">
			<tr>
			    <th>程序编号</th><th>程序名称</th><th>包名称</th><th>状态</th><th>创建时间</th><th>操作</th>
			</tr>
			
			<#if operator.softwares?exists>
			<#assign softwares =  operator.softwares/>
			<#list softwares as software>
			
			<#assign statusStr="有效" />
			<#if software.status!=0>
			<#assign statusStr="无效" />
			</#if>
			
			<tr>
			    <td>${software.appKey}</td><td>${software.name}</td><td>${software.packageName!""}</td><td>${statusStr}</td><td>${dateUtil.formatDate2String(software.getCreateDate())}</td><td><img src="images/detail.png" style="margin-right:20px;"/><img src="images/delete.png"/></td>
			</tr>
			</#list>
			</#if>
		</table>
	</div>
</body>
</html>