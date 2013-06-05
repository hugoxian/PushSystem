package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;
import com.xpush.android.xptp.dto.Result;

/**
 * 心跳包解析器
 * @author hugo
 *
 */
public class HeartbeatParser extends XPTPParser {

	@Override
	public void parser(Packet requestPacket, Packet responsePacket) {
		if(responsePacket instanceof Result){
			((Result) responsePacket).setMsgCode("0");
			((Result) responsePacket).setMsgDesc("Heartbeat Success!");
		}
	}

}
