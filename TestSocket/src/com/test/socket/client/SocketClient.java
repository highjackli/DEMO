package com.test.socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		

		try {
			for (int i = 0; i < 10; i++) {
				long start=System.currentTimeMillis();
				Socket s;
				s = new Socket("127.0.0.1", 5432);
				//			s = new Socket(InetAddress.getByName("127.0.0.1"), 10006);
				OutputStream out = s.getOutputStream(); 

				DataOutputStream dout = new DataOutputStream(out);
				InputStream in=null;

				dout.writeUTF("client");

				in = s.getInputStream();
				DataInputStream din = new DataInputStream(in);

				String st =din.readUTF();

				System.out.println("接收到服务端信息："+st);
				in.close();
				out.close();
				s.close(); 
				System.out.println(System.currentTimeMillis()-start);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 


	}

}
