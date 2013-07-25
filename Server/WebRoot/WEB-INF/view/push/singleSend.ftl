<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">
.input_class{
	background: #fff;
	border: solid 1px #C7C7C7; 
	padding: 1px; 
	width: 350px;
	height:30px;
}
</style>
</head>
<body>
	<div class="commonBody">
		<form id="pushForm" action="xxxx.do" method="post">
			<div>
				用户ID：<a href='#' class='linkbutton' onClick="del()" style="margin-right:20px;"><span>点击选择用户</span> </a>
   			</div>
   			<div>标 题：　<input  type="text" name="title" id="title" class = "input_class" style="margin-top:20px;"/> </div>
   			<div style="margin-top:20px;margin-bottom:20px;" >内 容：　<textarea name="message" id="message" cols ="50" rows = "5" class="input_class" style="height:80px;"></textarea></div>
			<div style="margin-bottom:10px;">注:消息标题不超过32个字，内容不超过140个字</div>
			<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>发送</span> </a>
		</form>
	</div>
</body>
</html>