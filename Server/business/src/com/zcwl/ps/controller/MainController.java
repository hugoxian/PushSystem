package com.zcwl.ps.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.cdr.CdrConstant;
import com.zcwl.cdr.CdrMeta;
import com.zcwl.ps.bo.AuthMananger;
import com.zcwl.ps.bo.Constants;
import com.zcwl.ps.constant.Constant;
import com.zcwl.ps.dto.NodeDto;
import com.zcwl.ps.dto.OperatorDto;
import com.zcwl.ps.dto.RoleDto;
import com.zcwl.tool.StringUtil;

/**
 * 
 * @author Hugo
 * 
 */
@Controller
public class MainController {

	protected static final Logger LOG = LoggerFactory
			.getLogger(MainController.class);

	@Autowired(required = true)
	private AuthMananger authMananger;

	/**
	 * 登录验证
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(ModelMap model, String username, String password,
			HttpSession session) {
		boolean isLogin = false;
		OperatorDto oper = null;
		try {
			// 判断用户名密码是否正确
			oper = this.authMananger.login(username, password);
			if (oper != null) {
				isLogin = true;
			}
			// 用缓存块中找出匹配的用户，包括了所属角色，节点权限等信息
			if (isLogin) {
				List<OperatorDto> operators = this.authMananger
						.getAllOperators();
				for (OperatorDto operator : operators) {
					if (oper.getId() == operator.getId()) {
						oper = operator;
						break;
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Login error:", e);
		}

		if (isLogin) {
			// 帐号密码正确，但操作者状态无效，或者操作者所属角色无效
			RoleDto role = oper.getRole();
			if (oper.getStatus() != 0 || role.getStatus() != 0) {
				model.addAttribute("result", 2);
			} else {
				// 登录成功，并把必要的信息放入会话中，记录CDR
				model.addAttribute("result", 0);

				CdrMeta cdr = new CdrMeta();
				cdr.actionType=CdrMeta.LOGIN;
				model.addAttribute(CdrConstant.CDR_META, cdr);
				session.setAttribute(CdrConstant.USER_ID, String.valueOf(oper.getId()));
				
				session.setAttribute(Constants.OPERATOR_LAST_LOGIN_DATE, oper
						.getLastLoginDate());

				// 如果之前该会话还存在，先清掉
				session.removeAttribute(Constants.OPERATOR);
				session.setAttribute(Constants.AUTH_TOKEN, StringUtil
						.generateUuid());
				oper.setLastLoginDate(new Date());
				session.setAttribute(Constants.OPERATOR, oper);
				
				try {
					this.authMananger.updateLoginDate(oper.getId(), oper
							.getLastLoginDate());
				} catch (Exception e) {
					LOG.error("updateLoginDate Error:", e);
				}

			}
		} else {
			model.addAttribute("result", 1);
		}

		return "common/result";
	}

	/**
	 * 进入首页
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(ModelMap model, HttpServletRequest request,
			HttpSession session) {
		String userAgent = request.getHeader(Constant.USER_AGNET_KEY);
		boolean isIe = false;
		if (userAgent.toLowerCase().contains("msie")) {
			isIe = true;
		}
		model.addAttribute("isIe", isIe);

		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);
		model.addAttribute("lastLoginDate", session
				.getAttribute(Constants.OPERATOR_LAST_LOGIN_DATE));
		model.addAttribute("name", operator.getName());
		model.addAttribute("roleName", operator.getRole().getName());

		return "home/index";
	}

	@RequestMapping("/init.do")
	public String initNode(ModelMap model, HttpSession session) {

		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		if (operator != null && operator.getStatus() == 0) {
			try {
				List<NodeDto> nodes = operator.getRole().getPermitNodes();
				model.addAttribute("nodes", nodes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "home/init";
	}

	/**
	 * 退出
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/exit.do")
	public String exit(ModelMap model, HttpSession session) {
		session.setAttribute(Constants.DESTROY_SESSION, true);
		CdrMeta cdr = new CdrMeta();
		cdr.actionType=CdrMeta.LOGOUT;
		model.addAttribute(CdrConstant.CDR_META, cdr);
		return "common/result";
	}

}
