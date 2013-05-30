package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;

/**
 * 解析器
 * 
 * @author hugo
 * 
 */
public abstract class XPTPParser {
	/**
	 * 
	 * @param requestPacket
	 * @param responsePacket
	 */
	public abstract void parser(Packet requestPacket, Packet responsePacket);
}
