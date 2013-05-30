package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;

/**
 * 执行解析
 * 
 * @author hugo
 * 
 */
public class ExecuteFilter implements XPTPFilter {

	/**
	 * 
	 */
	@Override
	public boolean filter(Packet requestPacket, Packet responsePacket)
			throws Exception {
		XPTPParser parser = requestPacket.getParser();
		if(parser!=null){
			parser.parser(requestPacket, responsePacket);
		}
		return false;
	}

}
