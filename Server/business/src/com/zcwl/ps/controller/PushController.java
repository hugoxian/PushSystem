package com.zcwl.ps.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.bo.Page;
import com.zcwl.ps.bo.Constants;
import com.zcwl.ps.bo.PsMananger;
import com.zcwl.ps.bo.PushManager;
import com.zcwl.ps.dto.OperatorDto;
import com.zcwl.ps.dto.PushTaskDto;
import com.zcwl.ps.dto.SoftwareDto;

/**
 * 个人信息相关的Controller
 * 
 * @author Hugo
 * 
 */
@Controller
public class PushController {

	@Autowired(required = true)
	private PushManager pushManager;

	/**
	 * 
	 * 群发
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/push/groupSend.do")
	public String groupSend(ModelMap model, HttpSession session) {
		return "push/groupSend";
	}

	/**
	 * 推送群发消息
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/push/pushGroupMsg.do")
	public String pushGroupMsg(ModelMap model, HttpSession session,
			String message, String deviceId, String title, int type) {
		int result = 0;

		if(title.length()>32){
			title = title.substring(0, 32);
		}
		
		if(message.length()>140){
			message = message.substring(0, 140);
		}
		
		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		List<SoftwareDto> softwares = operator.getSoftwares();

		SoftwareDto software = null;

		if (softwares != null && softwares.size() > 0) {
			software = softwares.get(0);
		}

		if (software != null) {
			PushTaskDto task = new PushTaskDto();
			task.setType(type);
			task.setOperatorId(operator.getId());
			task.setAppKey(software.getAppKey());
			task.setTitle(title);
			task.setChannel(PushTaskDto.CHANNEL_WEB);
			task.setContent(message);
			task.setCreateDate(new Date());
			// 默认即时发送
			task.setSendTime(task.getCreateDate());

			try {
				this.pushManager.createTask(task, deviceId);
			} catch (Exception e) {
				result = 1;
				e.printStackTrace();
			}
		} else {
			result = 2;
		}

		model.addAttribute("result", result);
		return "common/result";
	}

	/**
	 * 
	 * 单个发送
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/push/singleSend.do")
	public String singleSend(ModelMap model, HttpSession session) {
		return "push/singleSend";
	}

	/**
	 * 
	 * 推送记录
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/push/record.do")
	public String record(ModelMap model, HttpSession session) {

		OperatorDto operator = (OperatorDto) session
				.getAttribute(Constants.OPERATOR);

		List<SoftwareDto> softwares = operator.getSoftwares();

		SoftwareDto software = null;

		if (softwares != null && softwares.size() > 0) {
			software = softwares.get(0);
		}

		if (software != null) {

			Page<PushTaskDto> page = new Page<PushTaskDto>();
			page.setPageNo(1);
			page.setPageSize(15);
			try {
				this.pushManager.getTasks(page, software.getAppKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("tasks", page.getResult());
			model.addAttribute("dateUtil", PsMananger.dateUtil);
		}

		return "push/record";
	}
}