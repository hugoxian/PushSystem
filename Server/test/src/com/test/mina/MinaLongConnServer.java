package com.test.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 长连接服务
 * 
 * @author Hugo
 * 
 */
public class MinaLongConnServer {

	private static final int PORT = 8002;

	public void start() {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));
		acceptor.setHandler(new MinaLongConnServerHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		try {
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("MinaLongConnServer start success!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
