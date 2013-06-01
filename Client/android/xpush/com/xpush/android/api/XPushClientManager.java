package com.xpush.android.api;

import java.util.UUID;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.xpush.android.service.AlarmReceiver;
import com.xpush.android.service.MessageReceiver;
import com.xpush.android.service.MessageService;
import com.xpush.android.xptp.dto.Message;

/**
 * XPushClientManager
 * @author hugo
 * 
 */
public class XPushClientManager {

	public static final String KEY_APP_KEY = "XPUSH_APPKEY";

	public static String appKey;

	public static String deviceId;

	private static Context mContext;

	public final static String ACTION_MESSAGE_RECEIVED = "com.xpush.android.service.message";
	public final static String ACTION_ALARM_RECEIVED = "com.xpush.android.service.alarm";

	public final static String MSG_TYPE = "msgType";
	public final static String MSG_TITLE = "msgTitle";
	public final static String MSG_CONTENT = "msgContent";

	public final static String XPUSH_PREFERENCE = "XPUSH_PREFERENCE";
	public final static String CLIENT_DEVICEID = "XPUSH_CLIENT_DEVICEID";

	public static String registerMsgId;

	/**
	 * 初始化服务
	 * 
	 * @param context
	 */
	public static void init(Context context) {
		mContext = context;
		// 发送广播
		Intent intent = new Intent(context, AlarmReceiver.class);
		intent.setAction(ACTION_ALARM_RECEIVED);
		context.sendBroadcast(intent);
		appKey = getAppKey(context);
		deviceId = getDeviceId(context);
	}

	/**
	 * 启动服务
	 * 
	 * @param context
	 */
	public static void start(Context context) {
		Intent service = new Intent(context, MessageService.class);
		service.addFlags(MessageService.ACTION_START);
		context.startService(service);
	}

	/**
	 * 停止服务
	 * 
	 * @param context
	 */
	public static void stop(Context context) {
		Intent service = new Intent(context, MessageService.class);
		service.addFlags(MessageService.ACTION_STOP);
		context.startService(service);
	}

	private static Class<?> mReceiver;

	/**
	 * 设置接收器
	 * 
	 * @param receiver
	 */
	public static void setReceiver(Class<?> receiver) {
		mReceiver = receiver;
	}

	/**
	 * 
	 * @param msg
	 */
	public static void handleMessage(Message msg) {
		if (mReceiver == null) {
			mReceiver = MessageReceiver.class;
		}
		Intent intent = new Intent(mContext, mReceiver);
		intent.putExtra(MSG_TYPE, msg.getMsgType());
		intent.putExtra(MSG_TITLE, msg.getMsgTitle());
		intent.putExtra(MSG_CONTENT, msg.getMsgContent());
		intent.setAction(ACTION_MESSAGE_RECEIVED);
		mContext.sendBroadcast(intent);
	}

	// 取得AppKey
	private static String getAppKey(Context context) {
		Bundle metaData = null;
		String appKey = null;
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			if (ai != null) {
				metaData = ai.metaData;
			}

			if (metaData != null) {
				appKey = metaData.getString(KEY_APP_KEY);
			}
		} catch (NameNotFoundException e) {

		}
		return appKey;
	}

	/*
	 * 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID. Return null if device ID is not
	 * available
	 */
	private static String getDeviceId(Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				XPUSH_PREFERENCE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();

		String deviceId = settings.getString(CLIENT_DEVICEID, null);

		if (deviceId == null) {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			deviceId = tm.getDeviceId();
			if (deviceId != null && !"".equals(deviceId)) {
				editor.putString(CLIENT_DEVICEID, deviceId);
				editor.commit();
			}
		}

		return deviceId;
	}

	/**
	 * 设置ID
	 * 
	 * @param deviceId
	 */
	public static void setDeviceId(String newDeviceId) {
		SharedPreferences settings = mContext.getSharedPreferences(
				XPUSH_PREFERENCE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(CLIENT_DEVICEID, newDeviceId);
		editor.commit();
		deviceId=newDeviceId;
	}

	// 取得版本号
	public static String GetVersion(Context context) {
		try {
			PackageInfo manager = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return manager.versionName;
		} catch (NameNotFoundException e) {
			return "Unknown";
		}
	}

	/**
	 * 生成UUID
	 * 
	 * @return
	 */
	public static String generateUuid() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	public static String getUserAgent() {
		return "Android";
	}

}
