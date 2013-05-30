package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;

/**
 * 过滤器
 * 
 * @author hugo
 * 
 */
public interface XPTPFilter {
	/**
	 * 
	 * @param requestPacket
	 * @param responsePacket
	 * @throws Exception
	 */
	public boolean filter(Packet requestPacket, Packet responsePacket)
			throws Exception;
}
