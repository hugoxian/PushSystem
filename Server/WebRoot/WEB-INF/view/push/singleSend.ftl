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
		<form id="pushForm" action="xxxx.do" method="post">
			<div>
				用户ID：<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>点击选择用户</span> </a>
   			</div>
   			<div>标 题：<input  type="text" name="title" id="title" style="border: solid 1px #000000; padding: 1px; width: 350px;margin-top:10px;"/> </div>
   			<div style="margin-top:10px;margin-bottom:10px;" >内 容：<textarea name="message" id="message" cols ="50" rows = "5" style="border: solid 1px #000000; padding: 1px; width: 350px;"/></div>
			<div style="margin-bottom:10px;">注:消息标题不超过32个字，内容不超过140个字</div>
			<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>发送</span> </a>
		</form>
	</div>
</body>
</html>