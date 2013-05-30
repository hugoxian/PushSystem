package com.zcwl.tool;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Ip地址相关的工具类
 * 
 * @author Hugo
 * 
 */
public class IpUtil {

	/**
	 * 将域名解析为IP
	 * 
	 * @param hostName
	 * @return
	 */
	public static String host2ip(String hostName) {
		String ip = null;
		try {
			InetAddress ia = InetAddress.getByName(hostName);
			ip = ia.getHostAddress();
		} catch (IOException e) {
		}
		return ip;
	}

	/**
	 * 根据网卡取本机配置的IP 如果是双网卡的，则取出外网IP
	 * 
	 * @return
	 */
	public static String getHostIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface
				.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				// System.out.println(ni.getName() + ";" +
				// ip.getHostAddress()
				// + ";ip.isSiteLocalAddress()="
				// + ip.isSiteLocalAddress()
				// + ";ip.isLoopbackAddress()="
				// + ip.isLoopbackAddress());
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
	
	public static void main(String[] args){
		try {
			System.out.println(getHostIp());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
