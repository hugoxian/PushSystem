package com.zcwl.ps.vo;

/**
 * 登录话单VO
 * 
 * @author Hugo
 * 
 */
public class LoginCdrVo {
	private String time;
	private String ip;
	private String os;
	private String browser;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
