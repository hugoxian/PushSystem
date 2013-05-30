package com.zcwl.cdr;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hugo
 * 
 */
public class CdrLogger {

	private static CdrLogger cdrLogger = new CdrLogger();

	private CdrLogger() {
	}

	public static CdrLogger getInstance() {
		return cdrLogger;
	}

	private List<CdrMeta> cdrs;

	public List<CdrMeta> getCdrs() {
		if (cdrs == null) {
			cdrs = new ArrayList<CdrMeta>();
		}
		return cdrs;
	}

	public void clean() {
		this.cdrs = null;
	}

}
