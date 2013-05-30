package com.zcwl.xmpp.server.net;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xpush.android.api.XPushServerManager;
import com.zcwl.ps.bo.Constants;
import com.zcwl.xmpp.server.IoSessionManager;
import com.zcwl.xmpp.server.NewIoSessionManager;

/**
 * 
 * @author Hugo
 * 
 */
public class XmppIoHandler extends IoHandlerAdapter {
	private final static Logger LOG = LoggerFactory
			.getLogger(XmppIoHandler.class);

	private static int idleSecond;
	private static boolean needHeartbeatWhenIdle;

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, idleSecond);
		IoSessionManager.getInstance().add(session);
		session.setAttribute(Constants.CLIENT_REMOTE_ADDR, formatIp(session
				.getRemoteAddress().toString()));
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		LOG.warn("Unexpected exception.", cause);

		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
		try {
			XPushServerManager.handle(session, message.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) {
		// System.out.println("messageSent");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		LOG.info("session正空闲" + status.toString());
		// 如果需要发送心跳包
		if (needHeartbeatWhenIdle) {
			session.write("are you ok?");
		} else {
			// 手动关闭连接
			session.close(true);
		}

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		IoSessionManager.getInstance().del(session);
		NewIoSessionManager.getInstance().delIoSession(session);
	}

	public void setIdleSecond(int idleSecond) {
		this.idleSecond = idleSecond;
	}

	public void setNeedHeartbeatWhenIdle(boolean needHeartbeatWhenIdle) {
		this.needHeartbeatWhenIdle = needHeartbeatWhenIdle;
	}

	public String formatIp(String ip) {
		if (ip == null) {
			return null;
		}
		String[] temps = ip.split(":");
		return temps[0].substring(1, temps[0].length());

	}

}
