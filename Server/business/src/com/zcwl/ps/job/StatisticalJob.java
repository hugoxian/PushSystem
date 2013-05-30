package com.zcwl.ps.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zcwl.cdr.CdrMetaDao;
import com.zcwl.ps.dao.PushDao;

/**
 * 
 * @author hugo
 * 
 */
public class StatisticalJob extends QuartzJobBean {

	private static final Logger LOG = LoggerFactory
			.getLogger(StatisticalJob.class);

	private PushDao pushDao;

	public void setPushDao(PushDao pushDao) {
		this.pushDao = pushDao;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			this.pushDao.updateFinishCount();
		} catch (Exception e) {
			LOG.error("Update Finish Count Error:", e);
		}
	}
	

}
