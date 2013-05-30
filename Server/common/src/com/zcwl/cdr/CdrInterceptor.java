package com.zcwl.cdr;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zcwl.tool.UAUtil;

/**
 * 
 * CDR拦截器
 * 
 * @author Hugo
 */
public class CdrInterceptor extends HandlerInterceptorAdapter {

	protected static final Logger LOG = LoggerFactory
			.getLogger(CdrInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute(CdrConstant.START_TIME_KEY, System
				.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		CdrMeta cdr = null;
		if(modelAndView!=null){
			cdr = (CdrMeta) modelAndView.getModel().get(CdrConstant.CDR_META);
		}
		
		
		if (cdr != null) {
			request.setAttribute(CdrConstant.CDR_META, cdr);
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		CdrMeta cdr = (CdrMeta) request.getAttribute(CdrConstant.CDR_META);
		if (cdr != null) {
			cdr.version = CdrConstant.CDR_VERSION;
			
			String userId = (String) request.getSession().getAttribute(
					CdrConstant.USER_ID);
			if (userId != null) {
				cdr.userId = userId;
			} else {
				cdr.userId = "";
			}

			cdr.ua = request.getHeader(CdrConstant.USER_AGNET_KEY);
			boolean isPhone = false;
			if(cdr.ua==null){
				LOG.warn("some ua agent is null");
			}else{
				isPhone = UAUtil.isPhone(cdr.ua);
			}
			
			if (isPhone) {
				cdr.clientType = CdrMeta.TOUCE_SCREEN;
			} else {
				cdr.clientType = CdrMeta.WEB;
			}
			long startTime = (Long) request
					.getAttribute(CdrConstant.START_TIME_KEY);
			long processTime = System.currentTimeMillis() - startTime;

			Date startDate = new Date(startTime);
			cdr.eventTS = startDate;
			cdr.processTime = (int) processTime;
			cdr.clientIP = request.getRemoteHost();
//			if (cdr.remark == null) {
//				cdr.remark = "";
//			}
			if (cdr.actionType == CdrMeta.LOGOUT) {
				long createTime = request.getSession().getCreationTime();
				cdr.duration = (int) (System.currentTimeMillis() - createTime);
			}
			cdr.reason = 0;
			CdrLogger.getInstance().getCdrs().add(cdr);
		}
	}
}
