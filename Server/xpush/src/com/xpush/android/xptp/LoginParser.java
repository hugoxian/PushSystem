package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Login;
import com.xpush.android.xptp.dto.Packet;
import com.xpush.android.xptp.dto.Result;

/**
 * 
 * @author hugo
 * 
 */
public class LoginParser extends XPTPParser {

	/**
	 * 登陆监听器
	 */
	private LoginListener loginListener;

	@Override
	public void parser(Packet requestPacket, Packet responsePacket) {
		
		if(responsePacket instanceof Result){
			((Result) responsePacket).setMsgCode("0");
			((Result) responsePacket).setMsgDesc("Login Success!");
		}
		
		if (loginListener != null) {
			Login temp =(Login)requestPacket;
			loginListener.login(temp);
		}
	}

	public interface LoginListener {
		public void login(Login loginPacket);
	}

	public void setLoginListener(LoginListener loginListener) {
		this.loginListener = loginListener;
	}

}
