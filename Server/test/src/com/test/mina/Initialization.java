package com.test.mina;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

/**
 * 保存客户端的连接对象
 * 
 * @author Hugo
 * 
 */
public class Initialization {
	private static Initialization instance = new Initialization();

	/**
	 * 客户端连接map
	 */
	private Map<String, IoSession> clientMap = new HashMap<String, IoSession>();

	private Initialization() {

	}

	public static Initialization getInstance() {
		return instance;
	}

	public Map<String, IoSession> getClientMap() {
		return clientMap;
	}

	public void setClientMap(Map<String, IoSession> clientMap) {
		this.clientMap = clientMap;
	}
}
