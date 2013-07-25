<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">
table.gridtable tr:hover{
	background-color:#F5F5F5
}
table.gridtable tr:hover td{
	background:none;
}
</style>
<script type="text/javascript">
</script>

</head>
<body>
	<div class="commonBody">
		<table class="gridtable" id="table1">
			<tr>
			    <th>序号</th><th>请求URI</th><th>请求次数</th><th>平均处理时间（毫秒）</th><th>最近一次处理时间（毫秒）</th>
			</tr>
			<#list performances?keys as key>
			<#assign performance=performances[key] >
			<#assign time = performance.totalProcessTime/performance.times />
			<#if (time>performance.lastProcessTime)>
			<#assign color="blue"/>
			<#elseif time==performance.lastProcessTime>
			<#assign color="#000000"/>
			<#else>
			<#assign color="red"/>
			</#if>
			<tr>
			    <td>${key_index+1}</td><td>${key}</td><td>${performance.times}</td><td <#if (time>100)>style="color:red;"</#if>>${time}</td><td style="color:${color};">${performance.lastProcessTime}</td>
			</tr>
			</#list>
		</table>
		<div style="margin-top:5px;margin-bottom:5px;">注：第三列说明，红色表示平均处理时间大于100毫秒；最后一列说明，蓝色表示最后一次处理时间比平均处理时间少，红则反</div>
		<div style="margin-bottom:5px;margin-left:22px;">以上数据为该次服务器启动后的数据，供参考</div>
	</div>
</body>
</html>