package com.xpush.android.net;

/**
 * 和Xpush Server链接的配置
 * @author hugo
 *
 */
public class ConnectionConfig {
	private String host;
	private int port;
	private boolean isDebugMode;
	private int timeout;

	/**
	 * 
	 * @param host
	 * @param port
	 */
	public ConnectionConfig(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isDebugMode() {
		return isDebugMode;
	}

	public void setDebugMode(boolean isDebugMode) {
		this.isDebugMode = isDebugMode;
	}

}
