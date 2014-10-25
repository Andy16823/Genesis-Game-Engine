package com.types;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.physik.*;
import com.rendering.*;
import com.vector.*;

public class sprite {

	private String Name;
	private String Tag;
	private boolean Enabled;
	private gravity Gravity;
	private spritetextures Textures;
	private BufferedImage SelectedTexture;
	private size Size;
	private vector2 Location;
	
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
	
	public void setGravity(gravity Gravity)
	{
		this.Gravity = Gravity;
	}
	
	public void setSpriteTextures(spritetextures Textures)
	{
		this.Textures = Textures;
	}
	
	public void setSelectedTexture(BufferedImage Texture)
	{
		this.SelectedTexture = Texture;
	}
	
	public void setSize(size Size)
	{
		this.Size = Size;
	}
	
	public void setLocation(vector2 Location)
	{
		this.Location = Location;
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
	
	public gravity getGravity()
	{
		return this.Gravity;
	}
	
	public spritetextures getTextures()
	{
		return this.Textures;
	}
	
	public BufferedImage getSelectedTexture()
	{
		return this.SelectedTexture;
	}
	
	public size getSize()
	{
		return this.Size;
	}
	
	public vector2 getLocation()
	{
		return this.Location;
	}
	
	public sprite(String Name, vector2 Location, size Size, spritetextures Textures)
	{
		this.Name = Name;
		this.Tag = "Player";
		this.Enabled = true;
		this.Gravity = new gravity(false, 0);
		this.Textures = Textures;
		this.SelectedTexture = this.Textures.getTextureRight();
		this.Size = Size;
		this.Location = Location;
	}
	
	public sprite(String Name, vector2 Location, size Size, spritetextures Textures, gravity Gravity)
	{
		this.Name = Name;
		this.Tag = "Player";
		this.Enabled = true;
		this.Gravity = Gravity;
		this.Textures = Textures;
		this.SelectedTexture = this.Textures.getTextureRight();
		this.Size = Size;
		this.Location = Location;
	}
	
	public sprite(String Name, String Tag, vector2 Location, size Size, spritetextures Textures)
	{
		this.Name = Name;
		this.Tag = Tag;
		this.Enabled = true;
		this.Gravity = new gravity(false, 0);
		this.Textures = Textures;
		this.SelectedTexture = this.Textures.getTextureRight();
		this.Size = Size;
		this.Location = Location;
	}
	
	public sprite(String Name, String Tag, vector2 Location, size Size, spritetextures Textures, gravity Gravity)
	{
		this.Name = Name;
		this.Tag = Tag;
		this.Enabled = true;
		this.Gravity = Gravity;
		this.Textures = Textures;
		this.SelectedTexture = this.Textures.getTextureRight();
		this.Size = Size;
		this.Location = Location;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(this.Location.getX(), this.Location.getY(), this.Size.getWidth(), this.Size.getHeight());
	}
	
}
