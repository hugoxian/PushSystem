<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">
#tipsArea {
	color: red;
}
</style>
<script type="text/javascript">
	function groupSend() {
		var message = $("#message").val();
		var type =  $('input:radio[name=groupType]:checked').val();
		var title = $("#title").val();
		message = jQuery.trim(message);
		title=jQuery.trim(title);
		if ("" == message) {
			$("#tipsArea").html("请输入推送信息！");
			return;
		}
		
		$("#tipsArea").html("");
		
		$.post("pushGroupMsg.do", {message : message,type:type,title:title},
			function(data) {
				if ("0" == data) {
					$("#tipsArea").html("<span style='color:#000000;'>发送成功！</span>");
				} else if("1" == data) {
					$("#tipsArea").html("发送失败！");
				}  else if("2" == data) {
					$("#tipsArea").html("你没有绑定应用程序！");
				} 
			});
	}
</script>
</head>
<body>
	<div class="commonBody">
		<form id="pushForm" action="xxxx.do" method="post">
			<div>
				<input type="radio" id="groupType" name="groupType" value="1" checked />所有在线用户
	   			<input type="radio" id="groupType" name="groupType" value="2" />所有用户
   			</div>
   			<div>标 题：<input  type="text" name="title" id="title" style="border: solid 1px #000000; padding: 1px; width: 350px;margin-top:10px;"/> </div>
   			<div style="margin-top:10px;margin-bottom:10px;" >内 容：<textarea name="message" id="message" cols ="50" rows = "5" style="border: solid 1px #000000; padding: 1px; width: 350px;"></textarea></div>
			<div style="margin-bottom:10px;">注:消息标题不超过32个字，内容不超过140个字</div>
			<a href='#' class='linkbutton' onClick="groupSend()" style="margin-right:10px;"><span>发送</span> </a>
		</form>
		<div id="tipsArea" style="margin-top:10px;"></div>
	</div>
</body>
</html>