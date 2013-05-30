package com.zcwl.ps.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zcwl.ps.bo.Constants;
import com.zcwl.ps.dto.OperatorDto;

/**
 * 
 * @author Hugo
 * 
 */
public class UserSessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SessionMananger.getInstance().add(se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SessionMananger.getInstance().del(se.getSession());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if (event.getName().equals(Constants.OPERATOR)) {
			OperatorDto oper = (OperatorDto) event.getValue();
			oper.setOnline(true);
			oper.addLoginPlace();
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (event.getName().equals(Constants.OPERATOR)) {
			OperatorDto oper = (OperatorDto) event.getValue();
			oper.delLoginPlace();
			// 如果多处用户都退出了，则设置不在线
			if (oper.getLoginAtTheSameTime() == 0) {
				oper.setOnline(false);
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}
}
