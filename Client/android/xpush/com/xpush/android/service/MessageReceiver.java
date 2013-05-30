package com.xpush.android.service;

import com.xpush.android.api.XPushClientManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 
 * @author hugo
 * 
 */
public class MessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (XPushClientManager.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
			System.out.println("Has new InComeing Message!");
		}
	}

}