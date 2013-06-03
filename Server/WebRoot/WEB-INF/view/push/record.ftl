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
		<table class="gridtable">
			<tr>
			    <th>时间</th><th>接收者</th><th>标题</th><th>内容</th><th>推送方式</th><th>推送成功 / 满足条件</th><th>操作</th>
			</tr>
			<#if tasks?exists>
				<#list tasks as task>
				
				<#assign receiver="单个用户"/>
				<#if task.type==1>
				<#assign receiver="在线用户"/>
				<#elseif task.type==2>
				<#assign receiver="全部用户"/>
				<#elseif task.type==3>
				<#assign receiver="某地区用户"/>
				<#elseif task.type==4>
				<#assign receiver="某UA用户"/>
				<#elseif task.type==9>
				<#assign receiver="其他"/>
				</#if>
				
				<#assign channelStr = "API" />
				
				<#if task.channel==1>
				<#assign channelStr = "WEB" />
				</#if>
				
				<tr>
				    <td>${dateUtil.formatDate2String2(task.getSendTime())}</td><td>${receiver}</td><td>${task.title}</td><td>${task.content}</td><td>${channelStr}</td><td>${task.finishCount} / ${task.count}</td><td><img src="images/detail.png" style="margin-right:20px;"/><img src="images/delete.png"/></td>
				</tr>
				</#list>
			<#else>
				<tr>
			    <td colspan="8">No Push Record!</td>
			</tr>
			</#if>
		</table>
	</div>
	
	<div style="margin-bottom:20px;">注：推送成功数据有一定时间的延迟</div>
	
</body>
</html>