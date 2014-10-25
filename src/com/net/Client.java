package com.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class Client {

	private String Adress;
	private int Port;
	private Socket ClientSocket;
	private Vector<Command> Commands;
	
	public Client(String Server, int Port)
	{
		this.Commands = new Vector<Command>();
		this.Adress = Server;
		this.Port = Port;
	}
	
	public void Connect()
	{
		try {
			this.ClientSocket = new Socket(this.Adress, this.Port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCommand(com.net.util.Commands Command, Object ReciveObject)
	{
		Command cmd = new Command();
		cmd.Command = Command;
		cmd.Object = ReciveObject;
		this.Commands.add(cmd);
	}
	
	public Vector<Command> SendCommand()
	{
		Vector<Command> list = new Vector<Command>();
		try {
			
			byte[] sendbytes = com.net.util.PacketBuilder.PackObject(this.Commands);
			byte[] respbytes = null;
			
			OutputStream sw = this.ClientSocket.getOutputStream();
			InputStream is = this.ClientSocket.getInputStream();
			sw.write(sendbytes, 0, sendbytes.length);
			sw.flush();
			is.read(respbytes);
			list = (Vector<Command>) com.net.util.PacketBuilder.UnpackObject(respbytes);
			sw.close();
			is.close();
			this.ClientSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
}
