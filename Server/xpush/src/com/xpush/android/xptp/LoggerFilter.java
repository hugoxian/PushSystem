package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;

/**
 * 日志过滤器
 * 
 * @author hugo
 * 
 */
public class LoggerFilter implements XPTPFilter {

	@Override
	public boolean filter(Packet requestPacket, Packet responsePacket)
			throws Exception {
		return false;
	}

}
