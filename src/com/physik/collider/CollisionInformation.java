package com.physik.collider;

import com.types.gameobject;

public class CollisionInformation {

	private gameobject GameObject;
	private boolean IsCollsion;
	
	public CollisionInformation()
	{
		
	}
	
	public CollisionInformation(boolean IsCollision, gameobject GameObject)
	{
		this.GameObject = GameObject;
		this.IsCollsion = IsCollision;
	}
	
	public void setGameObject(gameobject GameObject)
	{
		this.GameObject = GameObject;
	}
	
	public void setCollision(boolean Enabled)
	{
		this.IsCollsion = Enabled;
	}
	
	public gameobject getGameObject()
	{
		return this.GameObject;
	}
	
	public boolean getCollision()
	{
		return this.IsCollsion;
	}
	
}
