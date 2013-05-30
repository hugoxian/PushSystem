package com.zcwl.ps.dao;

import java.util.Date;
import java.util.List;

import com.zcwl.bo.Page;
import com.zcwl.ps.dto.PushRecordDto;
import com.zcwl.ps.dto.PushTaskDto;

/**
 * 
 * @author hugo
 * 
 */
public interface PushDao {

	/**
	 * 增加一个推送任务
	 * 
	 * @param pushTask
	 * @return
	 * @throws Exception
	 */
	public PushTaskDto add(PushTaskDto task) throws Exception;

	public PushTaskDto addWithIdBack(final PushTaskDto task) throws Exception;

	/**
	 * 
	 * @param task
	 * @throws Exception
	 */
	public void updateTaskCount(PushTaskDto task) throws Exception;

	/**
	 * 增加一条记录
	 * 
	 * @param pushRecord
	 * @return
	 * @throws Exception
	 */
	public PushRecordDto add(PushRecordDto record) throws Exception;

	/**
	 * 批量插入记录
	 * 
	 * @param records
	 * @throws Exception
	 */
	public void batchInsert(List<PushRecordDto> records) throws Exception;

	/**
	 * 
	 * @param msgId
	 * @param resultCode
	 * @throws Exception
	 */
	public void updateRecord(String messageId, int pushStatus, Date finishTime)
			throws Exception;

	/**
	 * 批量更新记录
	 * 
	 * @param records
	 * @throws Exception
	 */
	public void batchUpdate(List<PushRecordDto> records) throws Exception;

	/**
	 * 取得推送任务列表
	 * 
	 * @param page
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public Page<PushTaskDto> getTasks(Page<PushTaskDto> page, String appKey)
			throws Exception;

	/**
	 * 取得推送记录列表
	 * 
	 * @param page
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public Page<PushRecordDto> getRecords(Page<PushRecordDto> page, int taskId)
			throws Exception;

	/**
	 * 更新完成的数据
	 * 
	 * @throws Exception
	 */
	public void updateFinishCount() throws Exception;
	
	/**
	 * 取得未发送消息
	 * @param appKey
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	public List<PushRecordDto> getUnSendingRecords(String appKey,String deviceId) throws Exception;
}
