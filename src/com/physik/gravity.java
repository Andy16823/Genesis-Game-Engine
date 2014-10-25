package com.physik;

public class gravity {

	// Variables
	private boolean Enabled;
	private int HorizontalValue;
	private int VerticalValue;
	
	public gravity(boolean Enabled, int Value)
	{
		this.Enabled = Enabled;
		this.HorizontalValue = Value;
		this.VerticalValue = Value;
	}
	
	public gravity(boolean Enabled, int HorizontalValue, int VerticalValue)
	{
		this.Enabled = Enabled;
		this.HorizontalValue = HorizontalValue;
		this.VerticalValue = VerticalValue;
	}
	
	public void setEnabled(boolean Enabled)
	{
		this.Enabled=Enabled;
	}
	
	public void setHorizontalValue(int Value)
	{
		this.HorizontalValue = Value;
	}
	
	public void setVerticalValue(int Value)
	{
		this.VerticalValue = Value;
	}
	
	public boolean getEnabled()
	{
		return this.Enabled;
	}
	
	public int getHorizontalValue()
	{
		return this.HorizontalValue;
	}
	
	public int getVerticalValue()
	{
		return this.VerticalValue;
	}
	
}
