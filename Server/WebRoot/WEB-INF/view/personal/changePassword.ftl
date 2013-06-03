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
		<form id="loginForm" action="index.do" method="post">
			<p>
				旧的密码 ：
				<input type="password" name="oldPassword" id="oldPassword"
					style="background: #fff; border: solid 1px #C7C7C7; padding: 1px; width: 140px;" />
			</p>
			<p>
				新的密码 ：
				<input type="password" name="newPassword" id="newPassword"
					style="background: #fff; border: solid 1px #C7C7C7; padding: 1px; width: 140px;" />
			</p>
			
			<p>
				重复密码 ：
				<input type="password" name="repeatPassword" id="repeatPassword"
					style="background: #fff; border: solid 1px #C7C7C7; padding: 1px; width: 140px;" />
			</p>

			<div id="tipsArea"></div>

			<a href='#' class='linkbutton' onClick="auth()"
				style='margin-left: 50px; margin-top: 10px;'><span>修改</span> </a>
		</form>
	</div>
</body>
</html>