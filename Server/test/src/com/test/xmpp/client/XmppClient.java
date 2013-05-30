package com.test.xmpp.client;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.ServiceDiscoveryManager;

public class XmppClient {

	 static ConnectionConfiguration connectionConfig = new ConnectionConfiguration(
			"127.0.0.1", 5222, "pc-20101218bbfw");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		ServiceDiscoveryManager manager = null;
		
//		connectionConfig.setDebuggerEnabled(true);

		XMPPConnection connection = new XMPPConnection(connectionConfig);
//		connection.addConnectionListener(conListener);
		connection.addPacketListener(packetListener, null);
		connection.connect();
		connection.login("hugo", "123456");
		System.out.println(connection.isUsingTLS());
		
//		connection.getAccountManager().createAccount("xian", "123456");
		
		
		
//		System.out.println(connection.getUser());
		
	}
	static PacketListener packetListener = new PacketListener(){

		@Override
		public void processPacket(Packet packet) {
			String xml = packet.toXML();
			System.out.println(xml);
		}
		
	};
	
	
	static ConnectionListener conListener = new ConnectionListener(){

		@Override
		public void connectionClosed() {
			
		}

		@Override
		public void connectionClosedOnError(Exception e) {
			
		}

		@Override
		public void reconnectingIn(int seconds) {
			
		}

		@Override
		public void reconnectionFailed(Exception e) {
			
		}

		@Override
		public void reconnectionSuccessful() {
			
		}
		
	};

}
