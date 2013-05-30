package com.zcwl.ps.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zcwl.ps.bo.Constants;

/**
 * 
 * 做一些权限检查等，重复登录暂不做考虑
 * 
 * @author Hugo
 */
public class PSInterceptor extends HandlerInterceptorAdapter {

	protected static final Logger LOG = LoggerFactory
			.getLogger(PSInterceptor.class);

	private String mappingURL;

	private static String timoutPagePath;

	private static String loginPagePath;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();

		String token = (String) request.getSession().getAttribute(
				Constants.AUTH_TOKEN);

		// 测试链接是不检查登录授权等信息，生产版本应去掉
		if (!uri.contains("login.do") && !uri.contains("/api/")
				&& !uri.contains("/test/")) {
			if (token != null) {
				return true;
			}
			String position = getParameterValueBykey("position", request);
			// 如果还在系统内点击，超时
			if ("here".equals(position)) {
				response.setStatus(response.SC_FORBIDDEN);
				if (timoutPagePath == null) {
					timoutPagePath = request.getContextPath()
							+ "/error/timeout.xml";
				}
				response.sendRedirect(timoutPagePath);
				return false;
			} else {
				if (loginPagePath == null) {
					loginPagePath = request.getContextPath() + "/index.html";
				}
				response.sendRedirect(loginPagePath);
				return false;
			}

		}

		return true;
	}

	private String getParameterValueBykey(String key, HttpServletRequest request) {
		String value = null;
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> set = map.entrySet();
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			if (key.equals(entry.getKey())) {
				value = entry.getValue()[0];
				break;
			}
		}
		return value;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}
}
