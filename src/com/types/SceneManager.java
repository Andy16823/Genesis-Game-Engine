package com.types;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Vector;

public class SceneManager {
	
	private String Name; 
	private String Tag;
	private boolean Enabled;
	private Vector<Scene> Scenes;
	private int NowScene;
	
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
	
	public void setScenes(Vector<Scene> Scenes)
	{
		this.Scenes = Scenes;
	}
	
	public void setSceneIndex(int Index)
	{
		this.NowScene = Index;
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
	
	public Vector<Scene> getScenes()
	{
		return this.Scenes;
	}
	
	public int getSceneIndex()
	{
		return this.NowScene;
	}
	
	public SceneManager()
	{
		this.Scenes = new Vector<Scene>();
	}
	
	public void AddScene(Scene scene)
	{
		this.Scenes.add(scene);
	}
	
	public Scene GetScene()
	{
		return this.Scenes.get(this.NowScene);
	}
	
	public Scene GetScene(String Name)
	{
		Scene returnvalue = null;
		for(Scene scene : this.Scenes)
		{
			if(scene.getName().equals(Name))
			{
				returnvalue = scene;
			}
		}
		return returnvalue;
	}
	
	public Vector<gameobject> GetGameObjects()
	{
		return this.GetScene().getGameObjects();
	}
	
	public void AddSceneLocationValue(int X, int Y, boolean ObjectsOffset)
	{
		this.GetScene().getLocation().setX(this.GetScene().getLocation().getX() + X);
		this.GetScene().getLocation().setY(this.GetScene().getLocation().getY() + Y);
		
		if(ObjectsOffset == true)
		{
			for(gameobject go : this.GetGameObjects())
			{
				go.getLocation().setX(go.getLocation().getX() + X);
				go.getLocation().setY(go.getLocation().getY() + Y);
			}
		}
	}
	
	public void AddSceneLocationX(int X, boolean ObjectsOffset)
	{
		this.GetScene().getLocation().setX(this.GetScene().getLocation().getX() + X);
		if(ObjectsOffset == true)
		{
			for(gameobject go : this.GetGameObjects())
			{
				go.getLocation().setX(go.getLocation().getX() + X);
			}
		}
	}
	
	public void AddSceneLocationY(int Y, boolean ObjectsOffset)
	{
		this.GetScene().getLocation().setY(this.GetScene().getLocation().getY() + Y);
		if(ObjectsOffset == true)
		{
			for(gameobject go : this.GetGameObjects())
			{
				go.getLocation().setY(go.getLocation().getY() + Y);
			}
		}
	}
	
	public BufferedImage BuildScene()
	{
		
		BufferedImage map = new BufferedImage(this.GetScene().getSize().getWidth(), this.GetScene().getSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = map.createGraphics();
		
		for(gameobject go : this.GetGameObjects())
		{
			if(go.getRenderMode() == gameobject.RenderModes.ON_GAME_BUILD)
			{
				g.drawImage(go.getTexture(), go.getLocation().getX(), go.getLocation().getY(), go.getSize().getWidth(), go.getSize().getHeight(), null);
			}
		}
		
		return map;
		
	}
	
	public Vector<gameobject> GetGameObjects(String Tag)
	{
		Vector<gameobject> list = new Vector<gameobject>();
		for(gameobject go : this.GetGameObjects())
		{
			if(go.getTag().equals(Tag))
			{
				list.add(go);
			}
		}
		return list;
	}
	
}
