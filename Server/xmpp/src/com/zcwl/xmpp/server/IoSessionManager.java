package com.zcwl.xmpp.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;

/**
 * 
 * @author Hugo
 *
 */
public class IoSessionManager {

	private static IoSessionManager instance = new IoSessionManager();

	private IoSessionManager() {
	}

	public static IoSessionManager getInstance() {
		return instance;
	}

	private List<IoSession> sessions = new ArrayList<IoSession>();

	public List<IoSession> getSessions() {
		return this.sessions;
	}

	/**
	 * 新增会话
	 * 
	 * @param session
	 */
	public void add(IoSession session) {
		sessions.add(session);
	}

	/**
	 * 删除会话
	 * 
	 * @param session
	 */
	public void del(IoSession session) {
		sessions.remove(session);
	}

}
