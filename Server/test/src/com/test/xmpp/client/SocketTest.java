package com.test.xmpp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Hugo
 * 
 */
public class SocketTest {
	private ServerSocket ss;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	SocketTest() {
		try {
			ss = new ServerSocket(5222);

			while (true) {
				socket = ss.accept();
				in = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				int temp = 0 ;
				while((temp =in.read())!=-1){
					System.out.print((char)temp);
					out.println("you input is :" + temp);
				}
				
//				String line = in.readLine();
//				out.println("you input is :" + line);
				out.close();
				in.close();
				socket.close();
			}
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		new SocketTest();
	}
}
