package com.zcwl.ps.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * 会话管理者
 * 
 * @author Hugo
 * 
 */
public class SessionMananger {
	private static SessionMananger instance = new SessionMananger();

	private SessionMananger() {
	}

	public static SessionMananger getInstance() {
		return instance;
	}

	private List<HttpSession> sessions = new ArrayList<HttpSession>();

	public List<HttpSession> getSessions(){
		return this.sessions;
	}
	
	/**
	 * 新增会话
	 * @param session
	 */
	public void add(HttpSession session) {
		sessions.add(session);
	}

	/**
	 * 删除会话
	 * @param session
	 */
	public void del(HttpSession session) {
		sessions.remove(session);
	}

}
