package com.test.socket.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {


	public static void main(String[] args) {
		ServerSocket s = null;
		try{
			s= new ServerSocket(5432);
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		int i = 1;
		while(true)
		{

			try{
				String hello = "From Server: Hello world";
				Socket sock = s.accept();
				//*********************************                 
				//                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
				//                String clientContent;  
				//                while ((clientContent = in.readLine()) != null) {  
				//                    System.out.println("Reveived: \n" + clientContent+"\n");  
				//                } 
				//                System.out.println("[SocketServer] 接收到客户端信息："+clientContent);
				//                OutputStream out = sock.getOutputStream();
				//                DataOutputStream dos = new DataOutputStream(out);
				//                dos.write("success".getBytes());
				//*********************************                
				//                InputStream in = sock.getInputStream();
				//                DataInputStream din = new DataInputStream(in);
				//                String clientContent = din.readUTF();
				//                System.out.println("[SocketServer] 接收到客户端信息："+clientContent);
				//                OutputStream out = sock.getOutputStream();
				//                DataOutputStream dos = new DataOutputStream(out);
				//                dos.writeUTF("server1111");

				//*********************************  
				InputStream in = sock.getInputStream();
				DataInputStream din = new DataInputStream(in);
				ByteArrayOutputStream byteOutput = null;
				String clientContent = "";
				byteOutput = new ByteArrayOutputStream();
				long start=System.currentTimeMillis();
				int read=0;
	             while ((read=din.read())!=64&&read!=-1) {//64==>"@" end with "@"
	           	  byteOutput.write(read);
					
				}
	             System.out.println("[SocketServer] 接收消息用去的时间(毫秒)："+(System.currentTimeMillis()-start));
				clientContent=new String(byteOutput.toByteArray(), "UTF-8");
				System.out.println("[SocketServer] 接收到客户端信息："+clientContent);
				OutputStream out = sock.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				dos.write("socket server@".getBytes());// end with "@"
//				dos.flush();
				in.close();
				din.close();
				out.close();
				dos.close();
				sock.close();
//				s.close();
				System.out.println("[SocketServer] 服务端请求结束！");
				i++;
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
}
