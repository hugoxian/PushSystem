package com.test.mina.heartbeat;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
	
	/** 心跳包内容 */
	private static final String HEARTBEATREQUEST = "HEARTBEATREQUEST";
	private static final String HEARTBEATRESPONSE = "HEARTBEATRESPONSE";

	/*
	 * 返回给服务器端的心跳包数据 返回结果才是服务器端收到的心跳包数据
	 */
	@Override
	public Object getRequest(IoSession session) {
		return HEARTBEATRESPONSE;
	}

	/*
	 * 获取服务器端的返回数据包
	 */
	@Override
	public Object getResponse(IoSession session, Object request) {
		return request;
	}

	/*
	 * 判断是否是服务器返回的心跳响应包此判断影响 KeepAliveRequestTimeoutHandler实现类 判断是否心跳包发送超时
	 */
	@Override
	public boolean isRequest(IoSession session, Object message) {
		if (message.equals(HEARTBEATREQUEST)) {
			System.out
					.println("服务器返回数据包，且该数据包是服务器对心跳包的相应后返回客户端数据包: " + message);
			return false;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 判断发送信息是否是心跳数据包此判断影响 KeepAliveRequestTimeoutHandler实现类 判断是否心跳包发送超时
	 */
	@Override
	public boolean isResponse(IoSession session, Object message) {
		if (message.equals(HEARTBEATRESPONSE)) {
			System.out.println("客户端引发心跳的数据包: " + message);
			return true;
		}
		return false;
	}
}
