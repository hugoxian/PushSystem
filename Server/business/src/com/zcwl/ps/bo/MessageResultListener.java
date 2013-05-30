package com.zcwl.ps.bo;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpush.android.xptp.ResultParser.ResultListener;
import com.xpush.android.xptp.dto.Result;
import com.zcwl.ps.dao.PushDao;

@Service
public class MessageResultListener implements ResultListener {

	/**
	 * 如果发送频繁，转为批量入库
	 */
	ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Autowired(required = true)
	private PushDao pushDao;

	@Override
	public void handle(final Result result) {
		final Date date = new Date();
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					pushDao.updateRecord(result.getId(), 9, date);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
