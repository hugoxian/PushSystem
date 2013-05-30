package com.xpush.android.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.xpush.android.api.XPushClientManager;

import android.util.Log;

/**
 * 
 * @author hugo
 * 
 */
public class XPushConnection {

	private final static String TAG = "XPushConnection";

	private ConnectionConfig config;

	private Socket client;

	private BufferedReader in;
	private PrintWriter out;

	private int noResponseTime;

	public XPushConnection(ConnectionConfig config) {
		this.config = config;
	}

	public boolean isConnect() {

		if (client == null) {
			return false;
		}

		return client.isConnected();
	}

	public void connect() {
		try {
			client = new Socket(config.getHost(), config.getPort());
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream(), "UTF-8"));
			out = new PrintWriter(client.getOutputStream(), true);
			// 接收线程
			new Thread() {
				@Override
				public void run() {
					read();
				}
			}.start();
			
			if (XPushClientManager.deviceId == null
					|| "".equals(XPushClientManager.deviceId)) {
				register();
			} else {
				login();
			}
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}

	public interface IncomeMessageListener {
		public void handleMessage(String msg, PrintWriter out);
		public void setConnection(XPushConnection connection);
	}

	public IncomeMessageListener incomeMessageListener;

	public void setIncomeMessageListener(
			IncomeMessageListener incomeMessageListener) {
		this.incomeMessageListener = incomeMessageListener;
	}

	private void read() {
		String line = null;
		if (in != null) {
			try {
				line = in.readLine();
			} catch (IOException e) {
			}
		}
		while (line != null) {
			noResponseTime = 0;
			if (incomeMessageListener != null) {
				incomeMessageListener.handleMessage(line, out);
			}
			if (in != null) {
				try {
					line = in.readLine();
				} catch (IOException e) {
					break;
				}
			}
		}

	}

	private void register() {
		String registerMsgId=XPushClientManager.generateUuid();
		XPushClientManager.registerMsgId=registerMsgId;
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xPush version=\"1.0\"><id>"
				+ registerMsgId
				+ "</id><type>REGISTER</type><message><appkey>"
				+ XPushClientManager.appKey
				+ "</appkey><deviceId></deviceId>"
				+ "</message></xPush>";
		if (out != null) {
			out.println(xml);
			out.flush();
		}
	}

	public void login() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xPush version=\"1.0\"><id>"
				+ XPushClientManager.generateUuid()
				+ "</id><type>LOGIN</type><message><appkey>"
				+ XPushClientManager.appKey
				+ "</appkey><deviceId>"
				+ XPushClientManager.deviceId
				+ "</deviceId><userAgent>"
				+ XPushClientManager.getUserAgent()
				+ "</userAgent><lonlat>lon,lat</lonlat></message></xPush>";
		if (out != null) {
			out.println(xml);
			out.flush();
		}
	}

	public void heartbeat() {
		if (noResponseTime >= 3) {
			disConnect();
			noResponseTime = 0;
			return;
		}
		noResponseTime++;
		String heartbeatXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xPush version=\"1.0\"><id>"
				+ XPushClientManager.generateUuid()
				+ "</id><type>HEARTBEAT</type><message><appkey>"
				+ XPushClientManager.appKey
				+ "</appkey><deviceId>"
				+ XPushClientManager.deviceId + "</deviceId></message></xPush>";
		if (out != null) {
			out.println(heartbeatXml);
			out.flush();
		}
	}

	public void sendMsg(String xml) {
		if (out != null) {
			out.println(xml);
			out.flush();
		}
	}

	public void reConnect() {
		disConnect();
		connect();
	}

	/**
	 * 停止链接
	 * 
	 * @throws Exception
	 */
	public void disConnect() {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
			}
			in = null;
		}
		if (out != null) {
			out.close();
			out = null;
		}

		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
			}
			client = null;
		}
	}

	/**
	 * 发送紧急数据检查数据链路
	 */
	public void checkLianlu() {
		try {
			client.sendUrgentData(0xFF);
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				disConnect();
			} catch (Exception e) {
			}
			Log.w(TAG, "Reconnection to Xpush Server!");
			connect();
		}
	}

}
