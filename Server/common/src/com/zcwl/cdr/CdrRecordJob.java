package com.zcwl.cdr;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 
 * @author Hugo
 * 
 */
public class CdrRecordJob extends QuartzJobBean {

	private static final Logger LOG = LoggerFactory
			.getLogger(CdrRecordJob.class);

	private CdrMetaDao cdrMetaDao;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {

		// this.countSession();
		List<CdrMeta> cdrs = CdrLogger.getInstance().getCdrs();
		CdrLogger.getInstance().clean();
		try {
			this.cdrMetaDao.batchInsert(cdrs);
			cdrs.clear();
		} catch (Exception e) {
			LOG.error("Insert the Cdr Record error:", e);
		}
	}

	private void countSession() {
		CdrMeta cdr = new CdrMeta();
		cdr.actionType = CdrMeta.ONLINE_COUNT;
		// cdr.remark = String.valueOf(OnlineCounter.getOnline());
		cdr.version = CdrConstant.CDR_VERSION;
		cdr.reason = 1;
		CdrLogger.getInstance().getCdrs().add(cdr);
	}

	public void setCdrMetaDao(CdrMetaDao cdrMetaDao) {
		this.cdrMetaDao = cdrMetaDao;
	}

}
