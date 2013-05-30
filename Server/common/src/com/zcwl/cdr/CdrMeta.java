package com.zcwl.cdr;

import java.io.Serializable;
import java.util.Date;

/**
 * CDR元数据定义
 * 
 * @author Hugo
 * 
 *         版本1.0
 * 
 */
public class CdrMeta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4626533115066816567L;
	
	
	
	public int id;
	public String version;
	public String userId;

	public String ua;
	/**
	 * 1、wap；2、3g；3、web；4、app；9、other
	 */
	public int clientType;
	/**
	 * APP客户端有这个属性
	 */
	public String clientVersion;

	/**
	 * 1、登入；2 、登出；3、在线人数
	 */
	public int actionType;
	/**
	 * 事件发生时间
	 */
	public Date eventTS;

	public String clientIP;
	/**
	 * 记录时间
	 */
	public Date createdTS;

	/**
	 * 处理耗时，单位毫秒，系统做性能分析用
	 */
	public int processTime;

	/**
	 * 登入登出类，记录回话时长及退出原因，时长，单位秒
	 */
	public int duration;
	/**
	 * 退出原因：0：user；1：erver
	 */
	public int reason;

	/**
	 * 不同的cdr记录该字段意义不一样
	 */
	public String remark;

	public final static int WAP = 1;
	public final static int TOUCE_SCREEN = 2;
	public final static int WEB = 3;
	public final static int APP = 4;
	public final static int OTHER = 9;

	// 这几个是所有系统都需记录的
	public final static int LOGIN = 1;
	public final static int LOGOUT = 2;
	public final static int ONLINE_COUNT = 3;

}
