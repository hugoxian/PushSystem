package com.zcwl.ps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.ps.bo.AuthMananger;
import com.zcwl.ps.dto.NodeDto;
import com.zcwl.ps.dto.OperatorDto;
import com.zcwl.ps.dto.RoleDto;

/**
 * 系统管理相关的Controller
 * 
 * @author Hugo
 * 
 */
@Controller
public class SystemController {

	protected static final Logger LOG = LoggerFactory
			.getLogger(SystemController.class);

	@Autowired(required = true)
	private AuthMananger authMananger;

	@RequestMapping("/system/nodesInfo.do")
	public String nodesInfo(ModelMap model, HttpSession session) {
		try {
			List<NodeDto> nodes = this.authMananger.getAllNodes();
			model.addAttribute("nodes", nodes);
		} catch (Exception e) {
			LOG.error("Get all Nodes infors error:", e);
		}
		return "system/nodesInfo";
	}

	@RequestMapping("/system/rolesInfo.do")
	public String rolesInfo(ModelMap model, HttpSession session) {
		try {
			List<RoleDto> roles = this.authMananger.getAllRoles();
			model.addAttribute("roles", roles);
		} catch (Exception e) {
			LOG.error("Get all Roles infor error:", e);
		}

		return "system/rolesInfo";
	}

	@RequestMapping("/system/operatersInfo.do")
	public String operatersInfo(ModelMap model, HttpSession session) {

		try {
			List<OperatorDto> operators = this.authMananger.getAllOperators();
			model.addAttribute("operators", operators);
		} catch (Exception e) {
			LOG.error("Get all Operator infor error:", e);
		}

		return "system/operatersInfo";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/system/opreateRecord.do")
	public String opreateRecord(ModelMap model, HttpSession session) {
		return "system/opreateRecord";
	}

}
