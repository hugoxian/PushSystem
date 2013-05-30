package com.xpush.android.net;

public class ConnectionConfig {
	private String host;
	private int port;
	private boolean isDebugMode;
	private int timeout;

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
