package com.test.mina.heartbeat;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class HeartBeatTestClient {

	private static final int PORT = 5222;
	/** 30秒后超时 */
	private static final int IDELTIMEOUT = 30;
	/** 15秒发送一次心跳包 */
	private static final int HEARTBEATRATE = 15;

	private static final String IPADDRESS = "127.0.0.1";

	private static NioSocketConnector connector;

	private static IoHandler handler = new HeartBeatClientHandler();

	public static void main(String[] args) {
		connector = new NioSocketConnector();
		connector.getFilterChain().addLast("process",
				new ExecutorFilter(Executors.newFixedThreadPool(4)));
		connector.getFilterChain().addLast("log", new LoggingFilter());
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
				IDELTIMEOUT);

		KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();

		KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();

		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
				IdleStatus.BOTH_IDLE, heartBeatHandler);

		/** 是否回发 */
		heartBeat.setForwardEvent(true);

		/** 发送频率 */
		heartBeat.setRequestInterval(HEARTBEATRATE);
		connector.getSessionConfig().setKeepAlive(true);
		connector.getFilterChain().addLast("heartbeat", heartBeat);

		connector.setHandler(handler);

		connector
				.setDefaultRemoteAddress(new InetSocketAddress(IPADDRESS, PORT));
		ConnectFuture connectFuture = connector.connect();
		connectFuture.awaitUninterruptibly();
		connectFuture.getSession().write("服务器我来啦！");
		connectFuture.awaitUninterruptibly();
		connectFuture.getSession().getConfig().setUseReadOperation(true);
		ReadFuture read = connectFuture.getSession().read();
		try {
			read.await();
			Object obj = read.getMessage();
			String text = null;
			if(obj!=null){
				text = obj.toString();
			}else{
				text="NULL";
			}
			System.out.println("服务器回复：  " + text);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// connector.dispose(true);
		}
	}

}
