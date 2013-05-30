package com.zcwl.ps.api;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.ps.bo.AuthMananger;
import com.zcwl.ps.bo.PushManager;
import com.zcwl.ps.dto.PushTaskDto;
import com.zcwl.tool.StringUtil;

/**
 * 对第三方应用的推送服务
 * 
 * @author Hugo
 * 
 */
@Controller
public class PushMsgController {

	@Autowired(required = true)
	private PushManager pushManager;
	@Autowired(required = true)
	private AuthMananger authMananger;

	/**
	 * 
	 * @param model
	 * @param session
	 * @param appKey
	 * @param typeStr
	 * @param deviceId
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping("/api/push/pushMsg.do")
	public String groupSend(ModelMap model, HttpSession session, String appKey,
			String typeStr, String deviceId, String title, String content) {

		int resultCode = 0;
		String resultMsg = null;

		if (StringUtil.isEmpty(appKey) || StringUtil.isEmpty(typeStr)
				|| StringUtil.isEmpty(title) || StringUtil.isEmpty(content)) {
			resultCode = 1;
			resultMsg = "参数有误";
		} else {
			int type = Integer.parseInt(typeStr);
			if (type == PushTaskDto.TASK_SINGLE && StringUtil.isEmpty(deviceId)) {
				resultCode = 1;
				resultMsg = "参数有误";
			} else {

				int operatorId = 0;
				try {
					operatorId = this.authMananger.getOpertorIdByAppKey(appKey);
					PushTaskDto task = new PushTaskDto();
					task.setType(type);
					task.setOperatorId(operatorId);
					task.setAppKey(appKey);
					task.setTitle(title);
					task.setChannel(PushTaskDto.CHANNEL_API);
					task.setContent(content);
					task.setCreateDate(new Date());
					// 默认即时发送
					task.setSendTime(task.getCreateDate());

					this.pushManager.createTask(task, deviceId);
				} catch (Exception e) {
					resultCode = 2;
					resultMsg = "服务器繁忙！";
					e.printStackTrace();
				}
			}
		}

		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);

		return "common/xmlresult";
	}

}