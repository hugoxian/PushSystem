package com.test.mina;

import java.net.InetSocketAddress;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 长连接服务处理
 * 
 * @author Hugo
 * 
 */
public class MinaLongConnServerHandler extends IoHandlerAdapter {
	protected static final Logger logger = LoggerFactory
			.getLogger(MinaLongConnServerHandler.class);

	@Override
	public void sessionCreated(IoSession session) {

	}

	@Override
	public void sessionOpened(IoSession session) {
		InetSocketAddress remoteAddress = (InetSocketAddress) session
				.getRemoteAddress();
		String clientIp = remoteAddress.getAddress().getHostAddress();
		logger.info("LongConnect Server opened Session ID ="
				+ String.valueOf(session.getId()));
		logger.info("接收来自客户端 :" + clientIp + "的连接.");
		Map<String, IoSession> clientMap = Initialization.getInstance()
				.getClientMap();
		clientMap.put(clientIp, session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
		logger.info("Messagereceived in the long connect server..");
		String expression = message.toString();
		logger.info("Message is:" + expression);
		IoSession shortConnSession = (IoSession) session
				.getAttribute("shortConnSession");
		logger.info("ShortConnect Server Session ID ="
				+ String.valueOf(shortConnSession.getId()));
		shortConnSession.write(expression);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		logger.info("Disconnectingthe idle.");
		session.close(true);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.warn(cause.getMessage(), cause);
		session.close(true);
	}

}
