package com.xpush.example;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

import com.xpush.android.api.XPushClientManager;
import com.xpush.example.receiver.MyMessageReceiver;

/**
 * 
 * @author hugo
 * 
 */
public class XpushExampleApplication extends Application {

	public static NotificationManager mNotificationManager;

	@Override
	public void onCreate() {

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// 初始化服务（建议在Application中初始化，也可以在Activity中初始化）
		XPushClientManager.init(this);
		// 设置程序自定义Notification接收器
		XPushClientManager.setReceiver(MyMessageReceiver.class);
	}
}
