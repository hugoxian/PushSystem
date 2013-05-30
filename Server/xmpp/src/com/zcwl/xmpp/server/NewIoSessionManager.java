package com.zcwl.xmpp.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import com.xpush.android.xptp.Constants;

/**
 * 
 * @author Hugo
 * 
 */
public class NewIoSessionManager {

	private static NewIoSessionManager instance = new NewIoSessionManager();

	private final static String CLOSE_BY_MANAGER = "CLOSE_BY_MANAGER";

	private NewIoSessionManager() {
	}

	public static NewIoSessionManager getInstance() {
		return instance;
	}

	private Map<String, Map<String, IoSession>> sessionMap = new ConcurrentHashMap<String, Map<String, IoSession>>();

	/**
	 * 根据程序取得会话列表
	 * 
	 * @param appKey
	 * @return
	 */
	public Map<String, IoSession> getIoSessionByAppkey(String appKey) {
		return sessionMap.get(appKey);
	}

	/**
	 * 
	 * @param appKey
	 * @param deviceId
	 * @return
	 */
	public IoSession getTargetOnlineSession(String appKey, String deviceId) {
		Map<String, IoSession> appSessions = sessionMap.get(appKey);
		if (appSessions == null) {
			return null;
		}

		return appSessions.get(deviceId);
	}

	/**
	 * 新增会话
	 * 
	 * @param session
	 */
	public void addIoSession(IoSession session) {
		String appKey = (String) session.getAttribute(Constants.APP_KEY);
		if (appKey == null) {
			return;
		}

		Map<String, IoSession> appSessionMap = sessionMap.get(appKey);
		if (appSessionMap == null) {
			appSessionMap = new ConcurrentHashMap<String, IoSession>();
			sessionMap.put(appKey, appSessionMap);
		}

		String deviceId = (String) session
				.getAttribute(Constants.PHONE_DEVICEID);
		if (deviceId == null) {
			return;
		}

		IoSession oldSession = appSessionMap.get(deviceId);

		if (oldSession != null) {
			oldSession.setAttribute(CLOSE_BY_MANAGER, true);
			oldSession.close(true);
		}

		appSessionMap.put(deviceId, session);

	}

	/**
	 * 删除会话
	 * 
	 * @param session
	 */
	public void delIoSession(IoSession session) {
		boolean isCloseByManager = false;
		if (session.getAttribute(CLOSE_BY_MANAGER) != null) {
			isCloseByManager = (Boolean) session.getAttribute(CLOSE_BY_MANAGER);
		}

		// 有新的代替原来，不可删除
		if (isCloseByManager) {
			return;
		}

		String appKey = (String) session.getAttribute(Constants.APP_KEY);
		if (appKey == null) {
			return;
		}
		Map<String, IoSession> appSessionMap = sessionMap.get(appKey);
		if (appSessionMap == null) {
			return;
		}
		String deviceId = (String) session
				.getAttribute(Constants.PHONE_DEVICEID);
		if (deviceId == null) {
			return;
		}

		appSessionMap.remove(deviceId);
	}

}
