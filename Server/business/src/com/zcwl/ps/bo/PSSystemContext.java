package com.zcwl.ps.bo;

/**
 * 系统上下文参数
 * 
 * @author Hugo
 * 
 */
public class PSSystemContext {

	private final static PSSystemContext instance = new PSSystemContext();

	public final static long MB = 1024 * 1024;

	/**
	 * 启动时间
	 */
	private long startTimestamp;

	/**
	 * 启动时间String类型，以免每次都格式化一次
	 */
	private String startTimeStr;

	/**
	 * 软件版本
	 */
	private String systemVersion = "1.0";

	/**
	 * jvm版本
	 */
	private String jvmVersion;
	
	/**
	 * 容器路径
	 */
	private String containerPath;

	/**
	 * 容器版本
	 */
	private String appServer;

	/**
	 * 软件路径
	 */
	private String appRealPath;

	/**
	 * 系统名称，如：linux、Windows 7
	 */
	private String osName;

	/**
	 * 硬件的一些信息
	 */
	private String hardware;

	private PSSystemContext() {

	}

	public static PSSystemContext getInstance() {
		return instance;
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getJvmVersion() {
		return jvmVersion;
	}

	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}

	public String getContainerPath() {
		return containerPath;
	}

	public void setContainerPath(String containerPath) {
		this.containerPath = containerPath;
	}

	public String getAppServer() {
		return appServer;
	}

	public void setAppServer(String appServer) {
		this.appServer = appServer;
	}

	public String getAppRealPath() {
		return appRealPath;
	}

	public void setAppRealPath(String appRealPath) {
		this.appRealPath = appRealPath;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getMemoryInfo() {
		Runtime runtime = Runtime.getRuntime();
		long totalMemory = runtime.totalMemory();
		long useMemory = totalMemory - runtime.freeMemory();

		long maxMemory = runtime.maxMemory();

		StringBuffer sb = new StringBuffer("已用内存：");
		sb.append(useMemory / MB).append("MB |").append(" 总内存：").append(
				totalMemory / MB).append("MB |").append(" 最大内存：").append(
				maxMemory / MB).append("MB");

		return sb.toString();
	}

}
