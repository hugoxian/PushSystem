package com.zcwl.ps.vo;

/**
 * 性能统计实体
 * 
 * @author Hugo
 * 
 */
public class PerformanceVo {

	/**
	 * 请求URI
	 */
	private String requestUri;
	
	/**
	 * 处理次数
	 */
	private int times;
	
	/**
	 * 总处理时间
	 */
	private int totalProcessTime;
	
	/**
	 * 最近一次处理时间
	 */
	private int lastProcessTime;

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getTotalProcessTime() {
		return totalProcessTime;
	}

	public void setTotalProcessTime(int totalProcessTime) {
		this.totalProcessTime = totalProcessTime;
	}

	public int getLastProcessTime() {
		return lastProcessTime;
	}

	public void setLastProcessTime(int lastProcessTime) {
		this.lastProcessTime = lastProcessTime;
	}
}
