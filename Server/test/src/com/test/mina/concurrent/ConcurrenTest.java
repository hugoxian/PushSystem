package com.test.mina.concurrent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrenTest {

	static ExecutorService es = Executors.newCachedThreadPool();

	static int count;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			es.submit(new Runnable() {

				@Override
				public void run() {
					try {
						Socket client = new Socket("192.168.1.108", 10010);
						PrintWriter out = new PrintWriter(client
								.getOutputStream(), true);
						login(out);

					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					while (true) {

					}
				}

				private void login(PrintWriter out) {
					count++;
					String imei = "2222" + count;
					String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xPush version=\"1.0\"><id>"
							+ "2222222222222222"
							+ "</id><type>LOGIN</type><message><appkey>"
							+ "6bdc4fccfdcdc811a202d0278375f408"
							+ "</appkey><imei>"
							+ imei
							+ "</imei><userAgent>"
							+ "2222222222222222"
							+ "</userAgent><lonlat>lon,lat</lonlat></message></xPush>";
					if (out != null) {
						out.println(xml);
						out.flush();
					}
				}

			});
		}
	}

}
