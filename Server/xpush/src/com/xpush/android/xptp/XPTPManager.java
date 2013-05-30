package com.xpush.android.xptp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zcwl.ps.bo.ClientLoginListener;
import com.zcwl.ps.bo.ClientRegisterListener;
import com.zcwl.ps.bo.MessageResultListener;
import com.zcwl.ps.servlet.StartListener;

/**
 * 
 * @author hugo
 * 
 */
public class XPTPManager {
	private static XPTPManager instance;

	private static List<XPTPFilter> filters = new ArrayList<XPTPFilter>();
	private static Map<String, XPTPParser> parsers = new HashMap<String, XPTPParser>();

	private static int clientIndex = 10000;

	public synchronized int getCliendIndex() {
		clientIndex++;
		return clientIndex;
	}
	
	public void setClientIndex(int newClientIndex){
		clientIndex = newClientIndex;
	}

	private XPTPManager() {

		// 这里代码耦合太紧，待优化

		LoginParser loginParser = new LoginParser();
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(StartListener.context);
		ClientLoginListener loginListener = (ClientLoginListener) ac
				.getBean("clientLoginListener");
		loginParser.setLoginListener(loginListener);

		RegisterParser registerParser = new RegisterParser();
		ClientRegisterListener registerListener = (ClientRegisterListener) ac
				.getBean("clientRegisterListener");
		registerParser.setRegisterListener(registerListener);

		ResultParser resultParser = new ResultParser();
		MessageResultListener resultListener = (MessageResultListener) ac
				.getBean("messageResultListener");
		resultParser.setResultListener(resultListener);

		parsers.put(Constants.TYPE_LOGIN, loginParser);
		parsers.put(Constants.TYPE_REGISTER, registerParser);
		parsers.put(Constants.TYPE_HEARTBEAT, new HeartbeatParser());
		parsers.put(Constants.TYPE_MESSAGE, new MessageParser());
		parsers.put(Constants.TYPE_RESULT, resultParser);

		filters.add(new IegalFilter());
		filters.add(new LoggerFilter());
		filters.add(new ExecuteFilter());
	}

	public static synchronized XPTPManager getInstance() {
		if (instance == null) {
			instance = new XPTPManager();
		}
		return instance;
	}

	public List<XPTPFilter> getFilters() {
		return filters;
	}

	public Map<String, XPTPParser> getParsers() {
		return parsers;
	}
}
