package com.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

import com.assets.asset;
import com.net.util.Commands;
import com.types.Scene;
import com.types.gameobject;
import com.types.sprite;

public class Listener{

	private Vector<Scene> Scenes;
	private Vector<gameobject> GameObjects;
	private Vector<sprite> Sprites;
	private Vector<asset> Assets;
	
	private java.net.ServerSocket ServerSocket;
	private int Port;
	private boolean run;
	
	public Listener(int Port)
	{
		this.Scenes = new Vector<Scene>();
		this.GameObjects = new Vector<gameobject>();
		this.Sprites = new Vector<sprite>();
		this.Assets = new Vector<asset>();
		this.Port = Port;
	}
	
	public void StartServer()
	{
		try {
			this.ServerSocket = new java.net.ServerSocket(this.Port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void HandleConnection()
	{
		while(this.run == true)
		{
			try {
				Socket Client = this.ServerSocket.accept();
				HwndlConnection hwndl = new HwndlConnection(this, Client);
				hwndl.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	public Vector<asset> getAssets()
	{
		return this.Assets;
	}
	
	public Vector<gameobject> getGameObjects()
	{
		return this.GameObjects;
	}
	
	public Vector<sprite> getSprites()
	{
		return this.Sprites;
	}
	
	public Vector<Scene> getScenes()
	{
		return this.Scenes;
	}
	
	public asset getAsset(String Name)
	{
		asset returnvalue = null;
		for(asset var : this.Assets)
		{
			if(var.getName().equals(Name))
			{
				returnvalue = var;
			}
		}
		return returnvalue;
	}
	
	public gameobject getGameObject(String Name)
	{
		gameobject returnvalue = null;
		for(gameobject var : this.GameObjects)
		{
			if(var.getName().equals(Name))
			{
				returnvalue = var;
			}
		}
		return returnvalue;
	}
	
	public Scene getScene(String Name)
	{
		Scene returnvalue = null;
		for(Scene var : this.Scenes)
		{
			if(var.getName().equals(Name))
			{
				returnvalue = var;
			}
		}
		return returnvalue;		
	}
	
	public sprite getSprite(String Name)
	{
		sprite returnvalue = null;
		for(sprite var : this.Sprites)
		{
			if(var.getName().equals(Name))
			{
				returnvalue = var;
			}
		}
		return returnvalue;
	}
	
	public void addAsset(asset Asset)
	{
		this.Assets.add(Asset);
	}
	
	public void addScene(Scene scene)
	{
		this.Scenes.add(scene);
	}
	
	public void addSprite(sprite Sprite)
	{
		this.Sprites.add(Sprite);
	}
	
	public void addGameObject(gameobject GameObject)
	{
		this.GameObjects.add(GameObject);
	}
}


class HwndlConnection extends Thread
{
	private Listener Server;
	private Socket Client;
	
	public HwndlConnection(Listener Server, Socket Client)
	{
		this.Server = Server;
		this.Client = Client;
	}
	
	@Override
	public void run()
	{
		try {
			
			InputStream is = this.Client.getInputStream();
			OutputStream os = this.Client.getOutputStream();
			
			byte[] inputbytes = null;
			is.read(inputbytes);
			
			Vector<Command> Commands = (Vector<Command>) com.net.util.PacketBuilder.UnpackObject(inputbytes);
			Vector<Command> Response = new Vector<Command>();
			for(Command cmd : Commands)
			{
				Command ResonseCmd = new Command();
				
				if(cmd.Command == com.net.util.Commands.CLIENT_GET_ASSETS)
				{
					ResonseCmd.Command = com.net.util.Commands.SERVER_SEND_ASSEST;
					ResonseCmd.Object = this.Server.getAssets();
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_GET_GAMEOBJECT)
				{
					String Name = (String) cmd.Object;
					ResonseCmd.Command = com.net.util.Commands.SERVER_SEND_GAMEOBJECT;
					ResonseCmd.Object = this.Server.getGameObject(Name);
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_GET_SCENE)
				{
					String Name = (String) cmd.Object;
					ResonseCmd.Command = com.net.util.Commands.SERVER_SEND_SCENE;
					ResonseCmd.Object = this.Server.getScene(Name);
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_GET_SCENES)
				{
					ResonseCmd.Command = com.net.util.Commands.SERVER_SEND_SCENES;
					ResonseCmd.Object = this.Server.getScenes();
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_GET_SPRITE)
				{
					String Name = (String) cmd.Object;
					ResonseCmd.Command = com.net.util.Commands.SERVER_SEND_SPRITE;
					ResonseCmd.Object = this.Server.getSprite(Name);
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_GET_SPRITES)
				{
					ResonseCmd.Command = com.net.util.Commands.SERVER_SEND_SPRITES;
					ResonseCmd.Object = this.Server.getSprites();
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_SEND_ASSETS)
				{
					Vector<asset> Value = (Vector<asset>) cmd.Object;
					for(asset var : Value)
					{
						this.Server.addAsset(var);
					}
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_SEND_GAMEOBJECT)
				{
					gameobject Value = (gameobject) cmd.Object;
					this.Server.addGameObject(Value);
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_SEND_GAMEOBJECTS)
				{
					Vector<gameobject> Value = (Vector<gameobject>) cmd.Object;
					for(gameobject var : Value)
					{
						this.Server.addGameObject(var);
					}
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_SEND_SCENE)
				{
					Scene Value = (Scene) cmd.Object;
					this.Server.addScene(Value);
				}
				else if(cmd.Command == com.net.util.Commands.CLIENT_SEND_SPRITE)
				{
					sprite Value = (sprite) cmd.Object;
					this.Server.addSprite(Value);
				}
				
				if(ResonseCmd.Command != null && ResonseCmd.Object != null)
				{
					Response.add(ResonseCmd);
				}
				
			}
			
			byte[] resonsebytes = com.net.util.PacketBuilder.PackObject(Response);
			os.write(resonsebytes, 0, resonsebytes.length);
			os.flush();
			is.close();
			os.close();
			this.Client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
