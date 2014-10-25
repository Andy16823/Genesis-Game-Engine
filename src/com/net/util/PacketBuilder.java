package com.net.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PacketBuilder {

	public static byte[] PackObject(Object object)
	{
		byte[] returnvalue = null;
		try {
			ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
			ObjectOutputStream ObjectStream = new ObjectOutputStream(ByteStream);
			ObjectStream.writeObject(object);
			ObjectStream.flush();
			returnvalue = ByteStream.toByteArray();
			ByteStream.close();
			ObjectStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnvalue;
	}
	
	public static Object UnpackObject(byte[] ObjectBytes)
	{
		Object returnvalue = null;
		try {
			
			ByteArrayInputStream ByteStream = new ByteArrayInputStream(ObjectBytes);
			java.io.ObjectInputStream ObjectStream = new ObjectInputStream(ByteStream);
			returnvalue = ObjectStream.readObject();
			ByteStream.close();
			ObjectStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnvalue;
	}
	
}
