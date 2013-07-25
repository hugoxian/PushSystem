<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
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
	<div><a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>新增</span> </a><a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>删除</span> </a><a href='#' class='linkbutton' onClick="del()"><span>编辑</span> </a></div>
	<hr/>
	<#list nodes as node>
		<div class="nodeItem"><input type="checkbox" name="checkbox" value="${node.id}" /> ${node.name}</div>
		<#if node.subNodes?exists>
		<#list node.subNodes as subNode>
		<div class="subNodeDetailItem"><input type="checkbox" name="checkbox" value="${subNode.id}" /> ${subNode.name}</div>
		</#list>
		</#if>
	</#list>
	</div>
</body>
</html>