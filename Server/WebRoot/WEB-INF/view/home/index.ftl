<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>推送系统</title> 
			<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
			<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
			<style type="text/css">
html {
	height:100%;
	overflow:hidden;
}

body {
	height:100%;
	margin: 0px;
	padding: 0px;
	font-size: 12px;
	overflow:hidden;
}

hr {
	height: 1px;
	border: none;
	border-top: 1px solid #C7C7C7;
}

img {
	border:0px;
}

a:link{text-decoration:none ; color:#666 ;}
a:visited {text-decoration:none ; color:#666 ;}
a:hover {text-decoration:underline ; color:#000 ;}
a:active {text-decoration:none ; color:#000 ;}

#topArea {
	width: 100%;
	height:50px;
	margin-top:5px;
	margin-bottom:5px;
}

#contentArea{
	width:100%;
}
#topBarArea{
	background: url('images/nav-bg.gif') repeat-x;
	height:27px;
	line-height: 27px;
	font-weight:bold;
	padding-left:10px;
}

.topBarNormal{
	width:100px;
	color:#000000;
	padding-left:8px;
	padding-right:8px;
}

.topBarSelected{
	width:100px;
	color:#ffffff;
}

#leftSideBarArea{
	float:left;
	width:200px;
	background:#F9F9F9;
	padding-left:10px;
	padding-top:10px;
}
#rightSideBarArea{
	float:right;
	background:#FEFEFE;
	overflow-x:hidden;
	overflow-y:auto;
}

#bottomArea {
	clear:both;
	background:#ffffff;
	position: absolute;
	bottom: 0;
	width: 100%;
	height:50px;
	padding: 5px;
	text-align: center;
	line-height: 20px;
}
#loading-page{
	position:absolute;
	top:0px;
	left:0px;
	background:#000000;
	width:100%;
	height:100%;
	filter:alpha(opacity=50);
	-moz-opacity:0.5;
	-khtml-opacity: 0.5;
	opacity: 0.5;
}
#loading-tips{
	width:300px;
	height:50px;
	color:#ffffff;
	text-align:center;
	margin:0 auto;
	margin-top:200px;
}
.subNodeNormal{
	display:none;
}
.subNodeSeleted{
	display:block;
}
.subNodeItem{
	margin-bottom:10px;
}
.commonBody{
	margin:10px 10px 1px 1px;
}

a.linkbutton {
	color: #444;
	background: url('images/button_a_bg.gif') no-repeat top right;
	font-size: 12px;
	text-decoration: none;
	display: inline-block;
	zoom: 1;
	height: 24px;
	padding-right: 18px;
	cursor: pointer;
	outline: none;
}

a.linkbutton span {
	display: inline-block;
	background: url('images/button_span_bg.gif') no-repeat top left;
	padding: 4px 0px 4px 18px;
	line-height: 16px;
	height: 16px;
}

a.linkbutton:hover {
	background-position: bottom right;
	outline: none;
}

a.linkbutton:hover span {
	background-position: bottom left;
}

table.gridtable {
	<#if isIe?exists&&isIe>
	width:99%;
	<#else>
	width:100%;
	</#if>
    font-family: verdana,arial,sans-serif;
    font-size:12px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
}
table.gridtable th {
    border-width: 1px;
    padding-top: 8px;
    padding-bottom: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #F5F5F5;
}
table.gridtable td {
    border-width: 1px;
    padding-top: 8px;
    padding-bottom: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
    text-align: center;
}
</style>
			<script type="text/javascript">
			
function initPostion() {
	var bodyWidth = document.body.clientWidth;
	var bodyHeight = document.body.clientHeight;
	
	var contentAreaHeight=bodyHeight-100;
	
	$("#contentArea").css('height',contentAreaHeight);
	
	var widthValue = bodyWidth - 220;
	var heightValue = contentAreaHeight-40;
	$("#rightSideBarArea").css('width', widthValue);
	$("#leftSideBarArea").css('height', heightValue);
	$("#rightSideBarArea").css('height', heightValue);
	
}

setTimeout(function(){init();}, "200");
window.onload = initPostion;
window.onresize = initPostion;
</script>
	</head>
	<body>
		<div id="topArea">
			<span style="margin-left:10px;"><img src='images/logo.png'/ style="margin-right:10px;">${name}，您的角色：${roleName}<#if lastLoginDate?exists>，上一次登录时间：${lastLoginDate?string('MM-dd HH:mm:ss')}</#if> <a href="#" onClick="exit();" style="text-decoration:underline;margin-left:10px;">退出</a></span>
		</div>
		<div id="contentArea">
			<div id="topBarArea">
			</div>
			<div id="leftSideBarArea"></div>
			<div id="rightSideBarArea"></div>
		</div>
		<div id="bottomArea">
			<hr/>
			<div>
				为了使得您有更好的浏览体验，请用Chrome浏览器
				<input type="hidden"
					value="56ul6Z6L77yM5om+5Yiw6L+Z6YeM5bmy5ZWl5ZWK77yM5Yir5bmy5Z2P5LqL5ZOmLS0t5Ya85b+X5YiX" />
			</div>
			<div>
				技术支持：深圳市众畅物联技术有限公司
			</div>
		</div>
		
		<div id='loading-page'>
			<div id='loading-tips'>正在加载中.....</div>
		</div>
		
	</body>
</html>