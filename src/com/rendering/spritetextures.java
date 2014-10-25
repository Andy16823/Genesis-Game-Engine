package com.rendering;

import java.awt.image.BufferedImage;

public class spritetextures {

	private BufferedImage TextureUp;
	private BufferedImage TextureDown;
	private BufferedImage TextureLeft;
	private BufferedImage TextureRight;
	
	public spritetextures(BufferedImage Texture)
	{
		this.TextureUp = Texture;
		this.TextureDown = Texture;
		this.TextureRight = Texture;
		this.TextureLeft = Texture;
	}
	
	public spritetextures(BufferedImage TextureLeft, BufferedImage TextureRight, BufferedImage TextureUp, BufferedImage TextureDown)
	{
		this.TextureUp = TextureUp;
		this.TextureDown = TextureDown;
		this.TextureLeft = TextureLeft;
		this.TextureRight = TextureRight;
	}
	
	public void setTextureUp(BufferedImage Texture)
	{
		this.TextureUp = Texture;
	}
	
	public void setTextureDown(BufferedImage Texture)
	{
		this.TextureDown = Texture;
	}
	
	public void setTextureLeft(BufferedImage Texture)
	{
		this.TextureLeft = Texture;
	}
	
	public void setTextureRight(BufferedImage Texture)
	{
		this.TextureRight = Texture;
	}
	
	public BufferedImage getTextureUp()
	{
		return this.TextureUp;
	}
	
	public BufferedImage getTextureDown()
	{
		return this.TextureDown;
	}
	
	public BufferedImage getTextureLeft()
	{
		return this.TextureLeft;
	}
	
	public BufferedImage getTextureRight()
	{
		return this.TextureRight;
	}
	
}
