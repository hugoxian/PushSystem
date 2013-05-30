package com.zcwl.ps.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.ps.bo.AuthMananger;
import com.zcwl.ps.bo.CdrMananger;
import com.zcwl.ps.bo.Constants;
import com.zcwl.ps.bo.PsMananger;
import com.zcwl.ps.dto.OperatorDto;
import com.zcwl.ps.vo.LoginCdrVo;

/**
 * 个人信息相关的Controller
 * 
 * @author Hugo
 * 
 */
@Controller
public class PersonalController {

	@Autowired(required = true)
	private CdrMananger cdrMananger;

	@Autowired(required = true)
	private AuthMananger authMananger;

	/**
	 * 个人资料
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/personal/profile.do")
	public String profile(ModelMap model, HttpSession session) {

		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		model.addAttribute("operator", operator);
		model.addAttribute("dateUtil", PsMananger.dateUtil);

		return "personal/profile";
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/personal/changePassword.do")
	public String changePassword(ModelMap model, HttpSession session) {
		return "personal/changePassword";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/personal/loginRecord.do")
	public String opreateRecord(ModelMap model, HttpSession session) {

		OperatorDto oper = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		try {
			Map<String, List<LoginCdrVo>> cdrs = cdrMananger
					.getLoginRecords(oper.getId());
			model.addAttribute("cdrs", cdrs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "personal/loginRecord";
	}
}
