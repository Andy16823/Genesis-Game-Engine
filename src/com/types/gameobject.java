package com.types;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.vector.*;

public class gameobject {
	
	public enum RenderModes{ON_GAME_BUILD, ON_GAME_PAINT }
	
	private String Name;
	private String Tag;
	private vector2 Location;
	private size Size;
	private boolean Enabled;
	private boolean IsCollider;
	private boolean IsTrigger;
	private RenderModes RenderMode;
	private BufferedImage Texture;
	
	public gameobject(String Name, vector2 Location, size Size, BufferedImage Texture)
	{
		this.Name = Name;
		this.Tag = "GameObject";
		this.Location = Location;
		this.Size = Size;
		this.Enabled = true;
		this.IsCollider = false;
		this.IsTrigger = false;
		this.RenderMode = RenderModes.ON_GAME_BUILD;
		this.Texture = Texture;
	}
	
	public gameobject(String Name, vector2 Location, size Size, RenderModes RenderMode, BufferedImage Texture)
	{
		this.Name = Name;
		this.Tag = "GameObject";
		this.Location = Location;
		this.Size = Size;
		this.Enabled = true;
		this.IsCollider = false;
		this.IsTrigger = false;
		this.RenderMode = RenderMode;
		this.Texture = Texture;
	}	
	
	public gameobject(String Name, vector2 Location, size Size, boolean IsCollider, BufferedImage Texture)
	{
		this.Name = Name;
		this.Tag = "GameObject";
		this.Location = Location;
		this.Size = Size;
		this.Enabled = true;
		this.IsCollider = IsCollider;
		this.IsTrigger = false;
		this.RenderMode = RenderModes.ON_GAME_BUILD;
		this.Texture = Texture;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(this.Location.getX(), this.Location.getY(), this.Size.getWidth(), this.Size.getHeight());
	}
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public void setTag(String Tag)
	{
		this.Tag = Tag;
	}
	
	public void setLocation(vector2 Location)
	{
		this.Location = Location;
	}
	
	public void setSize(size Size)
	{
		this.Size = Size;
	}
	
	public void setEnabled(boolean Enabled)
	{
		this.Enabled = Enabled;
	}
	
	public void setCollider(boolean Enabled)
	{
		this.IsCollider = Enabled;
	}
	
	public void setTrigger(boolean Enabled)
	{
		this.IsTrigger = Enabled;
	}
	
	public void setRenderMode(RenderModes Rendermode)
	{
		this.RenderMode = Rendermode;
	}
	
	public void setTexture(BufferedImage Texture)
	{
		this.Texture = Texture;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public String getTag()
	{
		return this.Tag;
	}
	
	public vector2 getLocation()
	{
		return this.Location;
	}
	
	public size getSize()
	{
		return this.Size;
	}
	
	public boolean getEnabled()
	{
		return this.Enabled;
	}
	
	public boolean getColliderState()
	{
		return this.IsCollider;
	}
	
	public boolean getTriggerState()
	{
		return this.IsTrigger;
	}
	
	public RenderModes getRenderMode()
	{
		return this.RenderMode;
	}
	
	public BufferedImage getTexture()
	{
		return this.Texture;
	}
}
