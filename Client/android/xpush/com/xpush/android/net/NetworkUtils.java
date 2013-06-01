package com.xpush.android.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

/**
 * 网络相关的一些工具方法
 * 
 * @author hugo
 * 
 */
public class NetworkUtils {

	public static final int NONETWORK = 0;
	public static final int WIFIACTIVE = 1;
	public static final int MOBILEACTIVE = 2;
	public static final int ALLACTIVE = 3;

	public static int getNetworkInfo(Context mContext) {
		Context context = mContext.getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State wifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		State mobile = connectivity.getNetworkInfo(
				ConnectivityManager.TYPE_MOBILE).getState();

		if (wifi == State.CONNECTED && mobile == State.CONNECTED) {
			return ALLACTIVE;
		}

		if (wifi == State.CONNECTED) {
			return WIFIACTIVE;
		}

		if (mobile == State.CONNECTED) {
			return MOBILEACTIVE;
		}

		return NONETWORK;
	}

	// startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
	// //进入无线网络配置界面

	// startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
	// //进入手机中的wifi网络设置界面

}
