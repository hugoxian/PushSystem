package com.xpush.android.service;

import com.xpush.android.api.XPushClientManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * 
 * @author hugo
 * 
 */
public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent mintent) {
		if (XPushClientManager.ACTION_ALARM_RECEIVED.equals(mintent.getAction())) {
			Intent intent = new Intent(context, MessageService.class);
			context.startService(intent);

			PendingIntent sender = PendingIntent.getService(context, 0, intent,
					0);
			long firstime = SystemClock.elapsedRealtime();
			firstime = firstime + 60 * 1000;
			AlarmManager am = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);

			// 60秒一个周期，不停的发送广播
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,
					60 * 1000, sender);
		}
	}

}
