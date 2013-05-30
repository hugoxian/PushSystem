package com.zcwl.ps.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zcwl.ps.bo.Constants;
import com.zcwl.ps.bo.PsMananger;
import com.zcwl.ps.constant.Constant;

/**
 * 性能监控
 * 
 * @author Hugo
 */
public class PerformanceInterceptor extends HandlerInterceptorAdapter {

	@Autowired(required = true)
	private PsMananger psMananger;
	
	protected static final Logger LOG = LoggerFactory
			.getLogger(PerformanceInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// Session首次创建记下用户IP
		if (request.getSession().isNew()) {
			request.getSession().setAttribute(Constants.REMOTE_ADDR,
					request.getRemoteAddr());
		}
		// 记下请求的时间戳
		request.setAttribute(Constant.START_TIME_KEY, System
				.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute(Constant.START_TIME_KEY);
		String requestUri = request.getRequestURI();

		String processTimeStr = String.valueOf(System.currentTimeMillis()
				- startTime);
		int processTime = Integer.parseInt(processTimeStr);
		psMananger.add(requestUri, processTime);
		// 如果是退出操作，注销Session
		if(request.getSession().getAttribute(Constants.DESTROY_SESSION)!=null){
			request.getSession().invalidate();
		}
	}
}
