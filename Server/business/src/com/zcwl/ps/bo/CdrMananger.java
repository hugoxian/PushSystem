package com.zcwl.ps.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwl.ps.dao.CdrDao;
import com.zcwl.ps.dto.CdrDto;
import com.zcwl.ps.vo.LoginCdrVo;
import com.zcwl.tool.UAUtil;

/**
 * 话单管理者
 * 
 * @author Hugo
 * 
 */
@Service
public class CdrMananger {
	protected static final Logger LOG = LoggerFactory
			.getLogger(CdrMananger.class);

	private static SimpleDateFormat formatDate = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static SimpleDateFormat formatTime = new SimpleDateFormat(
			"HH:mm:ss");

	@Autowired(required = true)
	private CdrDao cdrDao;

	/**
	 * 取得操作记录
	 * 
	 * Map中key为yyyy-MM-dd
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<LoginCdrVo>> getLoginRecords(int userId)
			throws Exception {
		List<CdrDto> cdrs = this.cdrDao.getLoginRecords(userId);
		Map<String, List<LoginCdrVo>> result = new LinkedHashMap<String, List<LoginCdrVo>>();
		for (CdrDto cdr : cdrs) {
			String eventDate = formatDate.format(cdr.getEventTime());

			List<LoginCdrVo> list = result.get(eventDate);
			if (list == null) {
				list = new ArrayList<LoginCdrVo>();
				result.put(eventDate, list);
			}
			LoginCdrVo loginCdr = new LoginCdrVo();
			loginCdr.setTime(formatTime.format(cdr.getEventTime()));
			loginCdr.setIp(cdr.getClientIp());

			String[] infos = UAUtil.getOsBrowserInfo(cdr.getUa());

			loginCdr.setOs(infos[0]);
			loginCdr.setBrowser(infos[1]);
			list.add(loginCdr);
		}
		return result;
	}
}
