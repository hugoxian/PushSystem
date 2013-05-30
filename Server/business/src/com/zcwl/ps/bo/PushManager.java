package com.zcwl.ps.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpush.android.xptp.dto.Message;
import com.zcwl.bo.Page;
import com.zcwl.ps.dao.PushDao;
import com.zcwl.ps.dto.PushRecordDto;
import com.zcwl.ps.dto.PushTaskDto;
import com.zcwl.tool.StringUtil;
import com.zcwl.xmpp.bo.MessageService;
import com.zcwl.xmpp.bo.MessageTask;
import com.zcwl.xmpp.server.NewIoSessionManager;

@Service
public class PushManager {
	protected static final Logger LOG = LoggerFactory
			.getLogger(PushManager.class);

	@Autowired(required = true)
	private PushDao pushDao;

	@Autowired(required = true)
	private ClientMananger clientMananger;

	/**
	 * 创建任务
	 * 
	 * @param type
	 * @param operatorId
	 * @param appKey
	 * @param tiltle
	 * @param content
	 * @param deviceId
	 * @throws Exception
	 */
	public void createTask(PushTaskDto task, String deviceId) throws Exception {
		task = this.pushDao.addWithIdBack(task);
		switch (task.getType()) {
		case PushTaskDto.TASK_SINGLE: {
			taskSingle(task, deviceId);
			break;
		}
		case PushTaskDto.TASK_ALL_ONLINE: {
			taskAllOnline(task);
			break;
		}
		case PushTaskDto.TASK_ALL: {
			taskAll(task);
			break;
		}
		}
	}

	/**
	 * 
	 * @param task
	 * @param deviceId
	 * @throws Exception
	 */
	private void taskSingle(PushTaskDto task, String deviceId) throws Exception {
		PushRecordDto record = new PushRecordDto();
		record.setTaskId(task.getId());
		record.setDeviceId(deviceId);
		record.setMessageId(StringUtil.generateUuid());
		record.setPushTime(task.getSendTime());
		record.setTitle(task.getTitle());
		record.setContent(task.getContent());
		task.setCount(1);
		this.pushDao.updateTaskCount(task);
		this.pushDao.add(record);

		Message msg = transformMessage(record);

		MessageTask msgTask = new MessageTask(task.getAppKey(), msg,
				task.getSendTime());
		MessageService.getInstance().startTask(msgTask);
	}

	private Message transformMessage(PushRecordDto record) {
		Message msg = new Message();
		msg.setDeviceId(record.getDeviceId());
		msg.setId(record.getMessageId());
		msg.setMsgContent(record.getContent());
		msg.setMsgTitle(record.getTitle());
		return msg;
	}

	/**
	 * 
	 * @param task
	 * @throws Exception
	 */
	private void taskAllOnline(PushTaskDto task) throws Exception {
		Map<String, IoSession> ioSessions = NewIoSessionManager.getInstance()
				.getIoSessionByAppkey(task.getAppKey());
		if (ioSessions != null && ioSessions.size() > 0) {
			List<PushRecordDto> records = new ArrayList<PushRecordDto>();
			for (Map.Entry<String, IoSession> entry : ioSessions.entrySet()) {
				PushRecordDto record = new PushRecordDto();
				record.setTaskId(task.getId());
				record.setDeviceId(entry.getKey());
				record.setMessageId(StringUtil.generateUuid());
				record.setPushTime(task.getSendTime());
				record.setTitle(task.getTitle());
				record.setContent(task.getContent());
				records.add(record);
			}
			task.setCount(records.size());
			this.pushDao.updateTaskCount(task);
			this.pushDao.batchInsert(records);

			if (records.size() > 0) {
				createMessageTask2Start(records,task.getAppKey(),task.getSendTime());
			}

		}
	}

	/**
	 * 
	 * @param task
	 * @throws Exception
	 */
	private void taskAll(PushTaskDto task) throws Exception {
		List<String> deviceIds = this.clientMananger.getAllClientDeviceIds(task
				.getAppKey());
		if (deviceIds != null && deviceIds.size() > 0) {
			List<PushRecordDto> records = new ArrayList<PushRecordDto>();
			for (String deviceId : deviceIds) {
				PushRecordDto record = new PushRecordDto();
				record.setTaskId(task.getId());
				record.setDeviceId(deviceId);
				record.setMessageId(StringUtil.generateUuid());
				record.setPushTime(task.getSendTime());
				record.setTitle(task.getTitle());
				record.setContent(task.getContent());
				records.add(record);
			}
			task.setCount(records.size());
			this.pushDao.updateTaskCount(task);
			this.pushDao.batchInsert(records);

			if (records.size() > 0) {
				createMessageTask2Start(records, task.getAppKey(), task.getSendTime());
			}

		}
	}

	//创建推送任务，并开始
	public void createMessageTask2Start(List<PushRecordDto> records, String appKey,
			Date sendTime) {
		List<Message> messages = new ArrayList<Message>();
		for (PushRecordDto record : records) {
			messages.add(this.transformMessage(record));
		}

		MessageTask msgTask = new MessageTask(appKey, messages, sendTime);
		MessageService.getInstance().startTask(msgTask);
	}
	
	/**
	 * 
	 * @param page
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public Page<PushTaskDto> getTasks(Page<PushTaskDto> page, String appKey)
			throws Exception {
		return this.pushDao.getTasks(page, appKey);
	}

}
