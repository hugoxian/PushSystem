package com.zcwl.ps.bo;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpush.android.xptp.LoginParser.LoginListener;
import com.xpush.android.xptp.dto.Login;
import com.zcwl.ps.dao.ClientDao;
import com.zcwl.ps.dao.PushDao;
import com.zcwl.ps.dto.ClientDto;
import com.zcwl.ps.dto.PushRecordDto;

@Service
public class ClientLoginListener implements LoginListener {

	@Autowired(required = true)
	private ClientDao clientDao;

	@Autowired(required = true)
	private PushDao pushDao;
	
	@Autowired(required = true)
	private PushManager pushManager;

	ExecutorService es = Executors.newFixedThreadPool(5);

	@Override
	public void login(final Login temp) {
		es.submit(new Runnable() {
			@Override
			public void run() {
				ClientDto client = new ClientDto();
				Date now = new Date();
				client.setAppKey(temp.getAppkey());
				client.setDeviceId(temp.getDeviceId());
				client.setLastLatLon(temp.getLonlat());
				client.setLastAccessTime(now);
				client.setUserAgent(temp.getUserAgent());

				try {
					ClientDto fromDatabase = clientDao.getClientByKeyAndId(
							client.getAppKey(), client.getDeviceId());

					if (fromDatabase == null) {
						client.setCreateDate(now);
						clientDao.insert(client);
					} else {
						clientDao.update(client);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					List<PushRecordDto> records = pushDao.getUnSendingRecords(
							client.getAppKey(), client.getDeviceId());
					if (records != null && records.size() > 0) {
						pushManager.createMessageTask2Start(records, client.getAppKey(), new Date());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

}
