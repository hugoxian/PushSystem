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
width: 250px;
height:30px;
}
</style>
</head>
<body>
	<div class="commonBody">
		<form id="form" action="xxx.do" method="post">
			<p>
				旧的密码 ：　
				<input type="password" name="oldPassword" id="oldPassword" class="input_class" />
			</p>
			<p>
				新的密码 ：　
				<input type="password" name="newPassword" id="newPassword" class="input_class" style="margin-top:10px;"/>
			</p>
			
			<p>
				重复密码 ：　
				<input type="password" name="repeatPassword" id="repeatPassword" class="input_class" style="margin-top:10px;"/>
			</p>

			<div id="tipsArea"></div>

			<a href='#' class='linkbutton' onClick="auth()"
				style='margin-left: 80px; margin-top: 20px;'><span>　修　改　</span> </a>
		</form>
	</div>
</body>
</html>