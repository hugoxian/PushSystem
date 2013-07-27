package com.xpush.example.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xpush.android.api.XPushClientManager;
import com.xpush.example.R;
import com.xpush.example.XpushExampleApplication;
import com.xpush.example.ui.MainActivity;

/**
 * 重写该类，实现自定义Notification样式
 * 
 * @author hugo
 * 
 */
public class MyMessageReceiver extends BroadcastReceiver{

	/**
	 * 
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		if (XPushClientManager.ACTION_MESSAGE_RECEIVED.equals(intent
				.getAction())) {
			showNotification(context, intent);
			System.out.println("This new Msg Comeing!");
		}
	}
	
	/**
	 * 
	 * @param context
	 */
	protected void showNotification(Context context, Intent intent) {

		Bundle extras = intent.getExtras();
		String msgType = extras.getString(XPushClientManager.MSG_TYPE);
		String msgTitle = extras.getString(XPushClientManager.MSG_TITLE);
		String msgContent = extras.getString(XPushClientManager.MSG_CONTENT);

		String appName = context.getResources().getString(R.string.app_name);

		NotificationManager nm = XpushExampleApplication.mNotificationManager;
		CharSequence from = appName;
		CharSequence message = msgContent;
		Intent i = new Intent(context, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, i,
				0);

		Notification notif = new Notification(R.drawable.ic_launcher, msgTitle,
				System.currentTimeMillis());
		notif.flags = Notification.FLAG_AUTO_CANCEL;
		notif.setLatestEventInfo(context, from, message, contentIntent);
		notif.vibrate = new long[] { 100, 250, 100, 500 };
		nm.cancel(R.string.app_name);
		nm.notify(R.string.app_name, notif);
	}

}
