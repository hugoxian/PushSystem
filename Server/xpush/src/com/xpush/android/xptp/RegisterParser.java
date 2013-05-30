package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;
import com.xpush.android.xptp.dto.Register;
import com.xpush.android.xptp.dto.Result;

/**
 * 
 * @author hugo
 *
 */
public class RegisterParser extends XPTPParser {

	/**
	 * 注册监听器
	 */
	private RegisterListener registerListener;

	public interface RegisterListener {
		public void register(Register register);
	}

	public void setRegisterListener(RegisterListener registerListener) {
		this.registerListener = registerListener;
	}

	@Override
	public void parser(Packet requestPacket, Packet responsePacket) {
		Register temp = (Register) requestPacket;
		
		if(temp.getDeviceId()==null||"".equals(temp.getDeviceId())){
			temp.setDeviceId(String.valueOf(XPTPManager.getInstance().getCliendIndex()));
		}
		
		
		if (responsePacket instanceof Result) {
			((Result) responsePacket).setMsgCode("0");
			((Result) responsePacket).setMsgDesc(temp.getDeviceId());
		}

		if (registerListener != null) {
			registerListener.register(temp);
		}
	}

}
