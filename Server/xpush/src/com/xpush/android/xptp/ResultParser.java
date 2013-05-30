package com.xpush.android.xptp;

import com.xpush.android.xptp.dto.Packet;
import com.xpush.android.xptp.dto.Result;

/**
 * 消息解析器
 * 
 * @author hugo
 * 
 */
public class ResultParser extends XPTPParser {

	private ResultListener resultListener;

	@Override
	public void parser(Packet requestPacket, Packet responsePacket) {
		if (resultListener != null) {
			Result result = (Result) requestPacket;
			resultListener.handle(result);
		}
	}

	public interface ResultListener {
		public void handle(Result result);
	}

	public void setResultListener(ResultListener resultListener) {
		this.resultListener = resultListener;
	}

}
