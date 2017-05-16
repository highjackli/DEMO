package com.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MuleSocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket socket;
		try {
			socket=new Socket(InetAddress.getByName("127.0.0.1"),10003);
			//******************************
//			//send message to server side
//			OutputStream os =  socket.getOutputStream();  
//			DataOutputStream bos = new DataOutputStream(os);  
//			//bos.writeUTF(this.esbMessage);  
//			bos.writeUTF("rwerwrwtttttttttttttttttttt");
//			bos.flush();   
//
//			//receive msg from server side
//			InputStream is = socket.getInputStream();  
//			DataInputStream dis = new DataInputStream(is);  
//			System.out.println("[MuleSocketClient] get message from tcp server: "+dis.readUTF());  
			//******************************
//			  BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));          
//			  String s;  
//			  while ((s = in.readLine()) != null) {  
//			      System.out.println("Reveived: " + s);  
//			  } 
//			 clientContent=dis.readUTF();
			//******************************
			//send message to server side
			OutputStream os =  socket.getOutputStream();  
			DataOutputStream bos = new DataOutputStream(os);  
			bos.write("socket client@".getBytes());// end with "@"
			bos.flush();   

			//receive msg from server side
			InputStream is = socket.getInputStream();  
			  String clientContent = "";
			  DataInputStream dis = new DataInputStream(is);  
			 ByteArrayOutputStream byteOutput = null;
           
             byteOutput = new ByteArrayOutputStream();
             int read=0;
             while ((read=dis.read())!=64) {//64==>"@" end with "@"
           	  byteOutput.write(read);
			}
             clientContent=new String(byteOutput.toByteArray(), "UTF-8");
			System.out.println("[MuleSocketClient] get message from tcp server: "+clientContent);  

		} catch (IOException e) {
			e.printStackTrace();
		}  
		 
	
	}

}
