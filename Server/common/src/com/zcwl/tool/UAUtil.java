package com.zcwl.tool;

/**
 * UA工具类
 * 
 * @author Hugo
 * 
 */
public class UAUtil {
	/**
	 * 根据UA信息判断终端是否为手机
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isPhone(String userAgent) {

		if (userAgent == null) {
			return false;
		}

		if (userAgent.contains("iPhone") || userAgent.contains("Android")
				|| userAgent.contains("SymbianOS")
				|| userAgent.contains("Windows Phone OS")
				|| userAgent.contains("BlackBerry")) {
			return true;
		}
		return false;
	}

	/**
	 * 根据UA信息判断终端操作系统及浏览器型号;有待完善
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String[] getOsBrowserInfo(String userAgent) {

		userAgent = userAgent.toLowerCase();

		String[] result = new String[2];
		String os = null;
		if (userAgent.contains("windows")) {
			os = "Windows";
		} else if (userAgent.contains("mac")) {
			os = "Mac";
		} else if (userAgent.contains("linux")) {
			os = "Linux";
		} else {
			os = "未知";
		}
		result[0] = os;

		String browser = null;
		if (userAgent.contains("msie")) {
			browser = "IE";
		} else if (userAgent.contains("chrome")) {
			browser = "Chrome";
		} else if (userAgent.contains("safari")) {
			browser = "Safari";
		} else if (userAgent.contains("firefox")) {
			browser = "Firefox";
		} else {
			browser = "未知";
		}
		result[1] = browser;
		return result;
	}

	/**
	 * 向路径中插入UA信息
	 * 
	 * @param path
	 * @param screenKey
	 * @return
	 */
	public static String insertUserAgentToPath(String path, String uaPath) {
		if (path == null || "".equals(path)) {
			return path;
		}

		return uaPath + "/" + path;
	}
}
