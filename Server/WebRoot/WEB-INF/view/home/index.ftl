<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>推送系统</title> 
			<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
			<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
			<script type="text/javascript" src="js/index.js"></script>
			<link href="css/index.css" rel="Stylesheet" type="text/css" />
			<style type="text/css">

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

function SetWinHeight(obj) {
	var win = obj;
	if (document.getElementByIdx_x) {
		if (win && !window.opera) {
			if (win.contentDocument
					&& win.contentDocument.body.offsetHeight)
				win.height = win.contentDocument.body.offsetHeight;
			else if (win.Document && win.Document.body.scrollHeight)
				win.height = win.Document.body.scrollHeight;
		}
	}
}

setTimeout(function(){init();}, "200");
window.onload = initPostion;
window.onresize = initPostion;
</script>
<style type="text/css">
html {
	height: 100%;
	overflow: hidden;
}
</style>
	</head>
	<body>
		<div id="topArea">
			<span style="margin-left:10px;"><img src='images/logo.png'/ style="margin-right:10px;">${name}，您的角色：${roleName}<#if lastLoginDate?exists>，上一次登录时间：${lastLoginDate?string('MM-dd HH:mm:ss')}</#if> <a href="#" onClick="exit();" style="text-decoration:underline;margin-left:10px;">退出</a></span>
		</div>
		<div id="contentArea">
			<div id="topBarArea">
			</div>
			<div id="leftSideBarArea"></div>
			<div id="rightSideBarArea">
				<iframe id="content" name="content" frameborder="0" width="100%" height="99%"></iframe>
			</div>
		</div>
		<div id="bottomArea">
			<hr/>
			<div>
				为了使得您有更好的浏览体验，请用Chrome浏览器
				<input type="hidden"
					value="56ul6Z6L77yM5om+5Yiw6L+Z6YeM5bmy5ZWl5ZWK77yM5Yir5bmy5Z2P5LqL5ZOmLS0t5Ya85b+X5YiX" />
			</div>
			<div>
				技术支持：BY hugo.xian#qq.com
			</div>
		</div>
		
		<div id='loading-page'>
			<div id='loading-tips'>正在加载中.....</div>
		</div>
		
	</body>
</html>