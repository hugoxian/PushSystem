package com.zcwl.ps.controller;

import java.util.Date;
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
import com.zcwl.tool.MD5Util;

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

	@RequestMapping("/system/addOperater.do")
	public String addOperater(ModelMap model, HttpSession session,
			String account, String password) {
		int result = 0;
		try {
			List<OperatorDto> operators = this.authMananger.getAllOperators();
			boolean isExist = false;
			for (OperatorDto operator : operators) {
				if (operator.getAccount().equals(account)) {
					isExist = true;
					break;
				}
			}
			//如果已经存在该用户名称
			if (isExist) {
				result = 1;
			}else{
				OperatorDto operator = new OperatorDto();
				operator.setAccount(account);
				operator.setPassword(MD5Util.getMD5String(password));
				operator.setCreateDate(new Date());
				operator.setName(account);
				//现固定只能添加程序注册者角色的操作者
				operator.setRoleId(2);
				this.authMananger.addOperator(operator);
			}

		} catch (Exception e) {
			result=2;
			LOG.error("Add Operator infor error:", e);
		}

		model.addAttribute("result", result);
		return "common/result";
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
