package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;

/**
 * 合法性检查，如果不是登陆包，检查是否登陆
 * 
 * @author hugo
 * 
 */
public class IegalFilter implements XPTPFilter {

	/**
	 * 
	 */
	@Override
	public boolean filter(Packet requestPacket, Packet responsePacket)
			throws Exception {
		return false;
	}

}
