package com.zcwl.ps.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwl.bo.Page;
import com.zcwl.ps.dao.ClientDao;
import com.zcwl.ps.dto.ClientDto;

/**
 * 
 * PS信息管理者
 * 
 * @author Hugo
 * 
 */
@Service
public class ClientMananger {

	private static final Logger LOG = LoggerFactory.getLogger(ClientMananger.class);
	
	@Autowired(required = true)
	private ClientDao clientDao;

	/**
	 * 
	 * @param page
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public Page<ClientDto> getClientsByKey(Page<ClientDto> page,String appKey)throws Exception{
		return this.clientDao.getClientsByKey(page,appKey);
	}
	
	/**
	 * 
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public List<String> getAllClientDeviceIds(String appKey)throws Exception{
		return this.clientDao.getAllClientDeviceIds(appKey);
	}
	
}
