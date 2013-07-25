<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">
.profileItem{
	margin-bottom:10px;
}
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

#update_win{
	position:absolute;
	top:0px;
	left:0px;
	width:100%;
	height:100%;
}
#update_area{
	margin: 0 auto;
	width:320px;
	height:300px;
	background:#F9F9F9;
	border:1px solid #dddddd;
	margin-top: 50px;
	padding:20px;
}

#tipsArea_up {
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
	$("#update_win").hide();
}
function add(){
	$("#tipsArea").hide();
	var softWareName = $("#softWareName").val();
	softWareName = jQuery.trim(softWareName);
	if ("" == softWareName) {
		$("#tipsArea").html("请输入程序名称！");
		$("#tipsArea").show();
		return;
	}

	var packageName = $("#packageName").val();
	packageName = jQuery.trim(packageName);

	if ("" == packageName) {
		$("#tipsArea").html("请输入包名称！");
		$("#tipsArea").show();
		return;
	}
	
	
	$.post("addSoftware.do", {
		softWareName : softWareName,
		packageName : packageName
	},function(data) {
				if ("0" == data) {
					window.open("profile.do","content");
				} else if ("1" == data) {
					$("#tipsArea").html("暂只支持一个用户一个应用！");
					$("#tipsArea").show();
				} else if ("2" == data) {
					$("#tipsArea").html("服务器繁忙，请稍后再试！");
					$("#tipsArea").show();
				}
			});
}

function go2update(id,status,softwareName,packageName){
	$("#update_win").show();
	$("#softWareName_up").val(softwareName);
	$("#packageName_up").val(packageName);
	$("#id_up").val(id);
	$("#status_up").val(status);
}

function update(){
	$("#tipsArea_up").hide();
	var softWareName = $("#softWareName_up").val();
	softWareName = jQuery.trim(softWareName);
	if ("" == softWareName) {
		$("#tipsArea_up").html("请输入程序名称！");
		$("#tipsArea_up").show();
		return;
	}

	var packageName = $("#packageName_up").val();
	packageName = jQuery.trim(packageName);

	if ("" == packageName) {
		$("#tipsArea_up").html("请输入包名称！");
		$("#tipsArea_up").show();
		return;
	}
	
	var id = $("#id_up").val();
	var status = $("#status_up").val();
	
	$.post("updateSoftware.do", {
		id : id,
		status : status,
		softWareName : softWareName,
		packageName : packageName
	},function(data) {
				if ("0" == data) {
					window.open("profile.do","content");
				} else if ("1" == data) {
					$("#tipsArea").html("暂只支持一个用户一个应用！");
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
		<a href='#' class='linkbutton' onClick="go2add()" style="margin-right:10px;"><span>新增程序</span> </a>
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
			    <td>${software.appKey}</td><td>${software.name}</td><td>${software.packageName!""}</td><td>${statusStr}</td><td>${dateUtil.formatDate2String(software.getCreateDate())}</td><td><a href="#" ><img src="../images/detail.png" onClick="go2update(${software.id},${software.status},'${software.name}','${software.packageName!""}')" style="margin-right:20px;"/></a><img src="../images/delete.png"/></td>
			</tr>
			</#list>
			</#if>
		</table>
	</div>
	
	<div id="new_win">
		<div id='new_area'>
			<form id="pushForm" action="xxxx.do" method="post">
				<div style="text-align: center;font-size:14px;font-weight:bold;">
					新建程序
				</div>
	   			<div>程序名称：　<input  type="text" name="softWareName" id="softWareName" style="border: solid 1px #000000; padding: 1px; width: 240px;height:30px;margin-top:20px;"/> </div>
	   			<div>包名称　：　<input  type="text" name="packageName" id="packageName" style="border: solid 1px #000000; padding: 1px; width: 240px;height:30px;margin-top:20px;"/> </div>
	   			<div style="width: 100%;margin-top:50px;">
				<a href='#' class='linkbutton' onClick="add()" style="margin-right:80px;"><span>　提　交　</span> </a>
				<a href='#' class='linkbutton' onClick="back()" style="margin-right:10px;"><span>　取　消　</span> </a>
				</div>
				<div id="tipsArea"></div>
			</form>
		</div>
	</div>
	
	<div id="update_win">
		<div id='update_area'>
			<form id="pushForm" action="xxxx.do" method="post">
				<div style="text-align: center;font-size:14px;font-weight:bold;">
					更新程序信息
				</div>
				<input type="hidden" id="id_up" />
	   			<div>程序名称：　<input  type="text" name="softWareName_up" id="softWareName_up" style="border: solid 1px #000000; padding: 1px; width: 240px;height:30px;margin-top:20px;"/> </div>
	   			<div>包名称　：　<input  type="text" name="packageName_up" id="packageName_up" style="border: solid 1px #000000; padding: 1px; width: 240px;height:30px;margin-top:20px;"/> </div>
	   			<div style="height:30px;margin-top:20px;">状　　态：　
		   			<select name="status_up" id="status_up" style="height:30px;width:240px;padding:0px;">  
				        <option value="0">有效</option>  
				        <option value="1">无效</option>  
			        </select>
		        </div>
	   			<div style="width: 100%;margin-top:50px;">
				<a href='#' class='linkbutton' onClick="update()" style="margin-right:80px;"><span>　更　新　</span> </a>
				<a href='#' class='linkbutton' onClick="back()" style="margin-right:10px;"><span>　取　消　</span> </a>
				</div>
				<div id="tipsArea_up"></div>
			</form>
		</div>
	</div>
</body>
</html>