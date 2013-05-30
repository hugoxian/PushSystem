package com.zcwl.ps.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.bo.Page;
import com.zcwl.ps.bo.ClientMananger;
import com.zcwl.ps.bo.Constants;
import com.zcwl.ps.bo.PsMananger;
import com.zcwl.ps.dto.ClientDto;
import com.zcwl.ps.dto.OperatorDto;
import com.zcwl.ps.dto.SoftwareDto;
import com.zcwl.xmpp.server.NewIoSessionManager;

/**
 * 个人信息相关的Controller
 * 
 * @author Hugo
 * 
 */
@Controller
public class ClientController {

	@Autowired(required = true)
	private ClientMananger clientMananger;

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/client/iosession.do")
	public String opreateRecord(ModelMap model, HttpSession session) {

		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		List<SoftwareDto> softwares = operator.getSoftwares();

		SoftwareDto software = null;

		if (softwares != null && softwares.size() > 0) {
			software = softwares.get(0);
		}

		if (software != null) {
			Map<String, IoSession> sessions = NewIoSessionManager.getInstance()
					.getIoSessionByAppkey(software.getAppKey());
			model.addAttribute("sessions", sessions);
		}

		model.addAttribute("nowTimeLong", System.currentTimeMillis());
		model.addAttribute("dateUtil", PsMananger.dateUtil);
		return "client/iosession";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/client/all.do")
	public String all(ModelMap model, HttpSession session) {
		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		List<SoftwareDto> softwares = operator.getSoftwares();

		SoftwareDto software = null;

		if (softwares != null && softwares.size() > 0) {
			software = softwares.get(0);
		}

		if (software != null) {
			Page<ClientDto> page = new Page<ClientDto>();
			page.setPageNo(1);
			page.setPageSize(20);
			try {
				List<ClientDto> clients = this.clientMananger.getClientsByKey(
						page, software.getAppKey()).getResult();
				model.addAttribute("clients", clients);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("dateUtil", PsMananger.dateUtil);
		return "client/all";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/client/analyse.do")
	public String analyse(ModelMap model, HttpSession session) {

		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		List<SoftwareDto> softwares = operator.getSoftwares();

		SoftwareDto software = null;

		if (softwares != null && softwares.size() > 0) {
			software = softwares.get(0);
		}

		if (software != null) {
			Map<String, IoSession> sessions = NewIoSessionManager.getInstance()
					.getIoSessionByAppkey(software.getAppKey());
			model.addAttribute("sessions", sessions);
		}

		model.addAttribute("nowTimeLong", System.currentTimeMillis());
		model.addAttribute("dateUtil", PsMananger.dateUtil);
		return "client/iosession";
	}
}
