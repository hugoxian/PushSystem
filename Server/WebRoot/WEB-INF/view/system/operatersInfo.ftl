<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">
#new_win{
	position:absolute;
	top:0px;
	left:0px;
	width:100%;
	height:100%;
}
#new_area{
	margin: 0 auto;
	width:320px;
	height:300px;
	background:#F9F9F9;
	border:1px solid #dddddd;
	margin-top: 50px;
	padding:20px;
}
#tipsArea {
	padding:10px;
	color: red;
}
</style>
<script type="text/javascript">
function go2add(){
	$("#new_win").show();
}
function back(){
	$("#new_win").hide();
}
function add(){
	$("#tipsArea").hide();
	var userAccount = $("#account").val();
	userAccount = jQuery.trim(userAccount);
	if ("" == userAccount) {
		$("#tipsArea").html("请输入帐号！");
		$("#tipsArea").show();
		return;
	}

	var password = $("#password").val();
	password = jQuery.trim(password);

	if ("" == password) {
		$("#tipsArea").html("请输入密码！");
		$("#tipsArea").show();
		return;
	}
	
	
	$.post("addOperater.do", {
		account : userAccount,
		password : password
	},function(data) {
				if ("0" == data) {
					window.open("operatersInfo.do","content");
				} else if ("1" == data) {
					$("#tipsArea").html("帐号已存在！");
					$("#tipsArea").show();
				} else if ("2" == data) {
					$("#tipsArea").html("服务器繁忙，请稍后再试！");
					$("#tipsArea").show();
				}
			});
}
</script>
</head>
<body onload="back();">
	<div class="commonBody">
		<a href='#' class='linkbutton' onClick="go2add()" style="margin-right:10px;"><span>新增操作员</span> </a>
		<hr/>
		<div style="height:10px;"></div>
		<table class="gridtable">
			<tr>
			    <th>名称</th><th>角色</th><th>状态</th><th>在线状态</th><th>最近一次登录时间</th><th>创建时间</th><th>操作</th>
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
			    <td <#if operator.status!=0>style="color:red;"</#if>>${operator.name}</td><td <#if roleStatus!=0>style="color:red;"</#if>>${roleName}</td><td>${statusStr}</td><td><img src="../images/${imgName}"/></td><td><#if operator.lastLoginDate?exists>${operator.lastLoginDate?datetime}<#else>尚未登录过</#if></td><td>${operator.createDate?string('yyyy-MM-dd HH:mm:ss')}</td><td><img src="../images/detail.png" style="margin-right:20px;"/><img src="../images/delete.png" style="margin-right:20px;"/><img src="../images/limit.png"/></td>
			</tr>
			</#list>
			</#if>
		</table>
		<div style="margin-top:5px;margin-bottom:5px;">注：红色代表操作员帐号、所属角色无效，不能登录</div>
	</div>
	
	<div id="new_win">
		<div id='new_area'>
			<form id="pushForm" action="xxxx.do" method="post">
				<div style="text-align: center;font-size:14px;font-weight:bold;">
					新建操作员
				</div>
				<div style="height:30px;margin-top:20px;">
					角　色：　程序注册者
	   			</div>
	   			<div>帐　号：　<input  type="text" name="account" id="account" style="border: solid 1px #000000; padding: 1px; width: 240px;height:30px;margin-top:10px;"/> </div>
	   			<div>密　码：　<input  type="password" name="password" id="password" style="border: solid 1px #000000; padding: 1px; width: 240px;height:30px;margin-top:20px;"/> </div>
	   			
	   			<div style="width: 100%;margin-top:50px;">
				<a href='#' class='linkbutton' onClick="add()" style="margin-right:80px;"><span>　提　交　</span> </a>
				<a href='#' class='linkbutton' onClick="back()" style="margin-right:10px;"><span>　取　消　</span> </a>
				</div>
				<div id="tipsArea"></div>
			</form>
		</div>
	</div>
	
</body>
</html>