package com.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class UIManager {

	private String Name;
	private String Tag; 
	private boolean Enabled;
	private Vector<Control> Controls;
	
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
	
	public UIManager()
	{
		this.Controls = new Vector<Control>();
	}
	
	public void AddControl(Control control)
	{
		this.Controls.add(control);
	}
	
	public void RemoveControl(Control control)
	{
		this.Controls.remove(control);
	}
	
	public Control GetControl(String Name)
	{
		Control returnvalue = null;
		for(Control control : this.Controls)
		{
			if(control.getName().equals(Name))
			{
				returnvalue = control;
			}
		}
		return returnvalue;
	}
	
	public void Paint(Graphics g)
	{
		for(Control control : this.Controls)
		{
			if(control.getEnabled()==true)
			{
				control.Paint(g);
			}
		}
	}
	
	public void Click(MouseEvent e)
	{
		for(Control control : this.Controls)
		{
			if(control.getEnabled() == true)
			{
				if(control.getBounds().contains(e.getPoint()))
				{
					control.OnClick(e);
				}
			}
		}
	}
	
	public void MouseUp(MouseEvent e)
	{
		for(Control control : this.Controls)
		{
			if(control.getEnabled() == true )
			{
				control.OnClickEnds(e);
			}
		}
	}
	
	public void MouseMove(MouseEvent e)
	{
		for(Control control : this.Controls)
		{
			if(control.getEnabled() == true)
			{
				control.OnMouseHover(e);
			}
		}
	}
	
	/**
	 * Returns a UI Control
	 * @param Name The Name of the Control
	 * @return
	 */
	public Control GetUIControl(String Name)
	{
		Control control = null;
		for(Control c : this.Controls)
		{
			if(c.getName().equals(Name))
			{
				control = c;
			}
		}
		return control;
	}
	
}
