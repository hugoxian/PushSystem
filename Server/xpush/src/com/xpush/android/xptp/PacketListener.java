package com.xpush.android.xptp;

import org.jivesoftware.smack.packet.Packet;

/**
 * 
 * @author hugo
 *
 */
public interface PacketListener {
	public void processPacket(Packet packet);
}
