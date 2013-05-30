package com.zcwl.ps.controller;

import java.net.SocketException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.ps.bo.PSSystemContext;
import com.zcwl.ps.bo.PsMananger;
import com.zcwl.ps.servlet.SessionMananger;
import com.zcwl.ps.vo.CacheVo;
import com.zcwl.tool.DateUtil;
import com.zcwl.tool.IpUtil;

/**
 * 服务器信息相关的Controller
 * 
 * @author Hugo
 * 
 */
@Controller
public class ServerInfoController {

	private static DateUtil dateUtil = new DateUtil();

	private static final Logger LOG = LoggerFactory
			.getLogger(ServerInfoController.class);

	@Autowired(required = true)
	private PsMananger psMananger;

	/**
	 * 进入首页
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/server/baseInfo.do")
	public String serverInfo(ModelMap model, HttpSession session) {
		PSSystemContext context = PSSystemContext.getInstance();

		model.addAttribute("startTime", context.getStartTimeStr());
		model.addAttribute("memoryInfo", context.getMemoryInfo());
		model.addAttribute("seconde", DateUtil.formatMs2String(System
				.currentTimeMillis()
				- context.getStartTimestamp()));
		model.addAttribute("systemVersion", context.getSystemVersion());
		model.addAttribute("jvmInfo", context.getJvmVersion());
		model.addAttribute("appServer", context.getAppServer());
		model.addAttribute("appRealPath", context.getAppRealPath());
		model.addAttribute("osInfo", context.getOsName() + "/"
				+ context.getHardware());

		try {
			model.addAttribute("ip", IpUtil.getHostIp());
		} catch (SocketException e) {
			LOG.error("get the host ip error:", e);
		}

		return "server/baseInfo";
	}

	@RequestMapping("/server/databaseInfo.do")
	public String databaseInfo(ModelMap model, HttpSession session) {
		return "server/go";
	}

	@RequestMapping("/server/cache.do")
	public String cacheInfo(ModelMap model, HttpSession session) {

		CacheVo[] caches = null;

		try {
			caches = psMananger.getCacheInfos();
		} catch (Exception e) {
			LOG.error("get cache Info error:", e);
		}
		model.addAttribute("caches", caches);
		return "server/cache";
	}

	@RequestMapping("/server/log.do")
	public String log(ModelMap model, HttpSession session) {

		String errorPath = psMananger.getErrorLogPath();

		try {
			String errorLog = psMananger.getErrorLog(errorPath);

			if (errorLog != null) {
				errorLog = errorLog.replaceAll("/r/n", "<br/>");
			}
			model.addAttribute("errorPath", errorPath);
			model.addAttribute("errorLog", errorLog);
		} catch (Exception e) {
			LOG.error("Get the error Log error:", e);
		}

		return "server/log";
	}

	/**
	 * 会话显示
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/server/session.do")
	public String session(ModelMap model, HttpSession session) {
		List<HttpSession> sessions = SessionMananger.getInstance()
				.getSessions();
		model.addAttribute("sessions", sessions);
		model.addAttribute("nowTimeLong", System.currentTimeMillis());
		model.addAttribute("dateUtil", dateUtil);
		return "server/session";
	}

	/**
	 * 剔除某个回话
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/server/kick.do")
	public String kick(ModelMap model, HttpSession session, String id) {

		if (session.getId().equals(id)) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", "不能剔除自己下线");
		} else {
			List<HttpSession> sessions = SessionMananger.getInstance()
					.getSessions();
			for (HttpSession temp : sessions) {
				if (temp.getId().equals(id)) {
					temp.invalidate();
					break;
				}
			}
			model.addAttribute("resultCode", 0);
			model.addAttribute("resultMsg", "删除成功");
		}
		
		return "common/xmlresult";
		
	}

	@RequestMapping("/server/performance.do")
	public String performance(ModelMap model, HttpSession session) {

		try {
			model.addAttribute("performances", psMananger.getPerformances());
		} catch (Exception e) {
			LOG.error("Get the performance infors：", e);
		}
		return "server/performance";
	}

	@RequestMapping("/go.do")
	public String go(ModelMap model, HttpSession session) {
		return "server/go";
	}

}
