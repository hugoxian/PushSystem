package com.zcwl.xmpp.bo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.mina.core.session.IoSession;

import com.xpush.android.xptp.dto.Message;
import com.zcwl.xmpp.server.NewIoSessionManager;

/**
 * 
 * @author hugo
 * 
 */
public class MessageService {

	private static MessageService instance = new MessageService();

	private MessageService() {

	}

	public static MessageService getInstance() {
		return instance;
	}

	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	public void startTask(final MessageTask task) {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				if (task.isSingle()) {
					IoSession session = NewIoSessionManager.getInstance()
							.getTargetOnlineSession(task.getAppKey(),
									task.getMessage().getDeviceId());
					if (session != null) {
						session.write(getMessageXml(task.getMessage()));
					}
				} else {

					if (task.getMessages() == null
							|| task.getMessages().size() == 0) {
						return;
					}

					for (Message message : task.getMessages()) {
						IoSession session = NewIoSessionManager.getInstance()
								.getTargetOnlineSession(task.getAppKey(),
										message.getDeviceId());

						if (session != null) {
							String xml = getMessageXml(message);
							session.write(xml);
						}
					}
				}
			}

			public String getMessageXml(Message message) {
				StringBuffer sb = new StringBuffer(
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?><xPush version=\"1.0\"><id>");
				sb.append(message.getId());
				sb.append("</id><type>MESSAGE</type>");
				sb.append("<message><msgType>1</msgType><msgTitle>");
				sb.append(message.getMsgTitle());
				sb.append("</msgTitle><msgContent>");
				sb.append(message.getMsgContent());
				sb.append("</msgContent></message></xPush>");
				return sb.toString();
			}
		});
	}
}
