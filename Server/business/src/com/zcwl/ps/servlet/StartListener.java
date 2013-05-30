package com.zcwl.ps.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xpush.android.xptp.XPTPManager;
import com.zcwl.ps.bo.AuthMananger;
import com.zcwl.ps.bo.PSSystemContext;
import com.zcwl.ps.dao.ClientDao;
import com.zcwl.tool.DateUtil;

/**
 * 启动监听器
 * 
 * @author Hugo
 * 
 */
public class StartListener implements ServletContextListener {

	protected static final Logger LOG = LoggerFactory
			.getLogger(StartListener.class);

	public static ServletContext context;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();
		initContext(sce);
		initData2Cache(sce);
	}

	/**
	 * 初始化部分数据到缓存块
	 * 
	 * @param sce
	 */
	private void initData2Cache(ServletContextEvent sce) {

		AuthMananger authMananger = (AuthMananger) this.getApplicationContext(
				sce).getBean("authMananger");
		try {
			// 服务器一启动就加载必要的数据
			authMananger.getAllNodes();
			authMananger.getAllRoles();
			authMananger.getAllOperators();
		} catch (Exception e) {
			LOG.error("Init datas error:", e);
		}

		ClientDao clientDao = (ClientDao) this.getApplicationContext(sce)
				.getBean("clientDao");

		try {
			int clientIndex = clientDao.getMaxClientIndex();
			if (clientIndex != 0) {
				XPTPManager.getInstance().setClientIndex(clientIndex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 取得Spring的上下文
	 * 
	 * @param sce
	 * @return
	 */
	private ApplicationContext getApplicationContext(ServletContextEvent sce) {
		return WebApplicationContextUtils.getWebApplicationContext(sce
				.getServletContext());
	}

	/**
	 * 取得一些服务器的参数
	 * 
	 * @param sce
	 */
	private void initContext(ServletContextEvent sce) {
		PSSystemContext context = PSSystemContext.getInstance();
		String osName = System.getProperty("os.name"); // 操作系统名称
		String osArch = System.getProperty("os.arch"); // 操作系统构架

		String jvmVersion = System.getProperty("java.version"); // jvm版本
		String jvmCompany = System.getProperty("java.vendor"); // jvm公司名称

		String containerPath = System.getProperty("catalina.home");// 容器路径
		String appServer = sce.getServletContext().getServerInfo();// 容器名称

		String appRealPath = sce.getServletContext().getRealPath("");

		// 启动时间
		long startTimestamp = System.currentTimeMillis();
		context.setStartTimestamp(startTimestamp);
		context.setStartTimeStr(DateUtil.formatLong2String(startTimestamp));
		context.setJvmVersion(jvmVersion + " " + jvmCompany);
		context.setContainerPath(containerPath);
		context.setAppServer(appServer);
		context.setAppRealPath(appRealPath);

		context.setOsName(osName);
		context.setHardware(osArch);
	}

}
