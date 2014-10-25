package com.types;

import java.util.Vector;

import com.vector.size;
import com.vector.vector2;

public class Scene {

	private String Name;
	private String Tag;
	private boolean Enabled;
	private Vector<gameobject> GameObjects;
	private vector2 Location;
	private size Size;
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public void setTag(String Tag)
	{
		this.Tag = Tag;
	}
	
	public void setEnabled(boolean Enabled)
	{
		this.Enabled = Enabled;
	}
	
	public void setGameObjects(Vector<gameobject> Objects)
	{
		this.GameObjects = Objects;
	}
	
	public void setLocation(vector2 Location)
	{
		this.Location = Location;
	}
	
	public void setSize(size Size)
	{
		this.Size = Size;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public String getTag()
	{
		return this.Tag;
	}
	
	public boolean getEnabled()
	{
		return this.Enabled;
	}
	
	public Vector<gameobject> getGameObjects()
	{
		return this.GameObjects;
	}
	
	public vector2 getLocation()
	{
		return this.Location;
	}
	
	public size getSize()
	{
		return this.Size;
	}
	
	public Scene()
	{
		this.GameObjects = new Vector<gameobject>();
	}
	
	public void AddGameObjekt(gameobject GameObject)
	{
		this.GameObjects.add(GameObject);
	}
	
	public gameobject GetGameObjekt(String Name)
	{
		gameobject returnvalue = null;
		for(gameobject go : this.GameObjects)
		{
			if(go.getName().equals(Name))
			{
				returnvalue = go;
			}
		}
		return returnvalue;
	}
	
	public void RemoveGameObject(gameobject GameObject)
	{
		this.GameObjects.remove(GameObject);
	}
	
	
}


