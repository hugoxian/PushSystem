package com.test.mina.heartbeat;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

public class KeepAliveRequestTimeoutHandlerImpl implements
		KeepAliveRequestTimeoutHandler {

	@Override
	public void keepAliveRequestTimedOut(KeepAliveFilter arg0, IoSession arg1)
			throws Exception {
		System.out.println("《*客户端*》心跳包发送超时处理(及长时间没有发送（接受）心跳包)");

	}

}
