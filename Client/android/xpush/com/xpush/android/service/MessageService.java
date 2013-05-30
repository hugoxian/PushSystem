package com.xpush.android.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.xpush.android.api.XPushIncomeManager;
import com.xpush.android.net.ConnectionConfig;
import com.xpush.android.net.NetworkUtils;
import com.xpush.android.net.XPushConnection;
import com.xpush.android.net.XPushConnection.IncomeMessageListener;

/**
 * 消息服务
 * 
 * @author hugo
 * 
 */
public class MessageService extends Service {

	private final static String TAG = "MessageService";

	private ConnectionConfig config;
	private XPushConnection connection;

	private ExecutorService executor;

	public final static int ACTION_START = 0;
	public final static int ACTION_STOP = 1;
	public final static int ACTION_RESUME = 2;
	public final static int ACTION_PAUSE = 3;

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		config = new ConnectionConfig("192.168.1.55", 5222);
		connection = new XPushConnection(config);

		IncomeMessageListener incomeMessageListener = new XPushIncomeManager();
		connection.setIncomeMessageListener(incomeMessageListener);
		incomeMessageListener.setConnection(connection);
		
		executor = Executors.newSingleThreadExecutor();
		if (NetworkUtils.getNetworkInfo(getApplicationContext()) != NetworkUtils.NONETWORK) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					connection.connect();
				}
			});
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (NetworkUtils.getNetworkInfo(getApplicationContext()) != NetworkUtils.NONETWORK) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					if (connection.isConnect()) {
						connection.heartbeat();
					} else {
						connection.reConnect();
					}
				}
			});
		}
		return Service.START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

}
