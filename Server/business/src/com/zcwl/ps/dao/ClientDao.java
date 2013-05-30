package com.zcwl.ps.dao;

import java.util.List;

import com.zcwl.bo.Page;
import com.zcwl.ps.dto.ClientDto;

/**
 * 
 * @author hugo
 * 
 */
public interface ClientDao {

	/**
	 * 通过程序key和设备ID取得Client
	 * 
	 * @param appKey
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	public ClientDto getClientByKeyAndId(String appKey, String deviceId)
			throws Exception;
	
	/**
	 * 
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public Page<ClientDto> getClientsByKey(Page<ClientDto> page,String appKey)throws Exception;
	
	
	public List<String> getAllClientDeviceIds(String appKey)throws Exception;

	/**
	 * 插入Client
	 * 
	 * @param client
	 * @return
	 * @throws Exception
	 */
	public ClientDto insert(ClientDto client) throws Exception;
	
	public void update(ClientDto client) throws Exception;

	public void batchInsert(List<ClientDto> clients) throws Exception;

	public void batchUpdate(List<ClientDto> clients) throws Exception;
	
	/**
	 * 取得最大index
	 * @return
	 * @throws Exception
	 */
	public int getMaxClientIndex()throws Exception;

}
