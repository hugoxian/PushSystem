package com.zcwl.ps.bo;

/**
 * 推送系统中应用到的常量
 * 
 * @author Hugo
 * 
 */
public interface Constants {
	// 用户通过认证令牌
	String AUTH_TOKEN = "auth_token";

	// 操作者
	String OPERATOR = "operator";
	
	String OPERATOR_LAST_LOGIN_DATE = "operator_last_login_time";

	// web用户的IP
	String REMOTE_ADDR = "remote_addr";

	// web销毁会话标识
	String DESTROY_SESSION = "destroy_session";

	// mina客户端的IP
	String CLIENT_REMOTE_ADDR = "client_remote_addr";
}
