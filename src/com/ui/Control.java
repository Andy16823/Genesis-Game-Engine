package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.game.Game;
import com.vector.size;
import com.vector.vector2;

public class Control {

	public enum BorderStyles{ NONE, NORMAL_STYLE, ROUNDED_STYLE };
	public enum ImageStyles{ NONE,NORMAL_DRAWING, STRECH_DRAWING};
	public enum TextStyles{ NONE, CENTER_ALLIGN, LEFT_ALLIGN};
	public enum ClickStates { LEFT_BUTTON, RIGHT_BUTTON, MIDDLE_BUTTON, NONE};
	
	/**
	 * Public Varaibls
	 */
	private String Name;
	private String Tag;
	private boolean Enabled;
	private BufferedImage BeckgroundImage;
	private Color BackgroundColor;
	private String Text;
	private Color ForeColor;
	private BorderStyles BorderStyle;
	private Color BorderColor;
	private ImageStyles ImageStyle;
	private TextStyles TextStyle;
	private java.awt.Font Font;
	private com.ui.events.OnClickListener OnClickListener;
	private com.ui.events.OnPaintListener OnPaintListener;
	private vector2 Location;
	private size Size;
	private ClickStates isClicked;
	private Game GamePanel;
	private boolean HoverEnabled;
	private Color HoverdColor;
	private BorderStyles HoveredBorderStyle;
	private Color HoveredBorderColor;
	private boolean isHoverd;
	

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
	
	public void setBackgroundImage(BufferedImage Image)
	{
		this.BeckgroundImage = Image;
	}
	
	public void setBackgroundColor(Color color)
	{
		this.BackgroundColor = color;
	}
	
	public void setText(String Text)
	{
		this.Text = Text;
	}
	
	public void setForeColor(Color color)
	{
		this.ForeColor = color;
	}
	
	public void setBorderStyle(BorderStyles Style)
	{
		this.BorderStyle = Style;
	}
	
	public void setBorderColor(Color color)
	{
		this.BorderColor = color;
	}
	
	public void setImageStyle(ImageStyles style)
	{
		this.ImageStyle = style;
	}
	
	public void setTextStyle(TextStyles style)
	{
		this.TextStyle = style;
	}
	
	public void setFont(java.awt.Font font)
	{
		this.Font = font;
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
	
	public BufferedImage getBackgroundImage()
	{
		return this.BeckgroundImage;
	}
	
	public Color getBackgroundColor()
	{
		return this.BackgroundColor;
	}
	
	public String getText()
	{
		return this.Text;
	}
	
	public Color getForeColor()
	{
		return this.ForeColor;
	}
	
	public BorderStyles getBorderStyle()
	{
		return this.BorderStyle;
	}
	
	public Color getBorderColor()
	{
		return this.BorderColor;
	}
	
	public ImageStyles getImageStyle()
	{
		return this.ImageStyle;
	}
	
	public TextStyles getTextStyle()
	{
		return this.TextStyle;
	}
	
	public java.awt.Font getFont()
	{
		return this.Font;
	}
	
	public vector2 getLocation()
	{
		return this.Location;
	}
	
	public size getSize()
	{
		return this.Size;
	}

	public com.ui.events.OnClickListener getOnClickListener()
	{
		return this.OnClickListener;
	}
	
	public com.ui.events.OnPaintListener getOnPaintLister()
	{
		return this.OnPaintListener;
	}
	
	/**
	 * Constructor
	 */
	public Control(Game game)
	{
		this.GamePanel = game;
		this.setBorderStyle(BorderStyles.NONE);
		this.setImageStyle(ImageStyles.NONE);
		this.setTextStyle(TextStyles.NONE);
		this.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
		this.setBackgroundColor(new Color(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue(), 220));
		this.setForeColor(Color.WHITE);
		this.setBorderColor(Color.WHITE);
		this.setEnabled(true);
		this.setName("Undefined");
		this.setHovered(true);
		this.setHoveredBorderColor(Color.WHITE);
		this.setHoveredBorderStyle(BorderStyles.NORMAL_STYLE);
		this.setHoveredColor(Color.BLACK);
	}
	
	public Control(Game game, String Name)
	{
		this.GamePanel = game;
		this.setBorderStyle(BorderStyles.NONE);
		this.setImageStyle(ImageStyles.NONE);
		this.setTextStyle(TextStyles.NONE);
		this.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
		this.setBackgroundColor(new Color(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue(), 220));
		this.setForeColor(Color.WHITE);
		this.setBorderColor(Color.WHITE);
		this.setEnabled(true);
		this.setName(Name);
		this.setHovered(true);
		this.setHoveredBorderColor(Color.WHITE);
		this.setHoveredBorderStyle(BorderStyles.NORMAL_STYLE);
		this.setHoveredColor(Color.BLACK);
	}
	
	/**
	 * Functions & Methods
	 */
	
	public void Paint(Graphics g)
	{
		
	}
	
	public void OnClick(MouseEvent e)
	{
		if(e.getButton()== MouseEvent.BUTTON1)
		{
			this.isClicked = ClickStates.LEFT_BUTTON;
		}
		else if(e.getButton()== MouseEvent.BUTTON2)
		{
			this.isClicked = ClickStates.RIGHT_BUTTON;
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			this.isClicked = ClickStates.MIDDLE_BUTTON;
		}
		else
		{
			this.isClicked = ClickStates.NONE;
		}
		if(this.OnClickListener != null)
		{
			this.OnClickListener.OnClick(this, this.isClicked);
		}
	}
	
	public void OnClickEnds(MouseEvent e)
	{
		this.isClicked = ClickStates.NONE;
	}
	
	public void OnMouseHover(MouseEvent e)
	{
		if(this.getBounds().contains(e.getX(), e.getY()))
		{
			this.isHoverd = true;
		}
		else
		{
			this.isHoverd = false;
		}
	}
	
	public ClickStates getClickState()
	{
		return this.isClicked;
	}
	
	public Game getHandler()
	{
		return this.GamePanel;
	}
	
	public void AddOnPaintListener(com.ui.events.OnPaintListener listener)
	{
		this.OnPaintListener = listener;
	}
	
	public void AddOnClickListener(com.ui.events.OnClickListener listener)
	{
		this.OnClickListener = listener;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(this.Location.getX(), this.Location.getY(), this.Size.getWidth(), this.Size.getHeight());
	}
	
	public void setHovered(boolean Enabled)
	{
		this.HoverEnabled = Enabled;
	}
	
	public boolean getHovered()
	{
		return this.HoverEnabled;
	}
	
	public void setHoveredColor(Color color)
	{
		this.HoverdColor = color;
	}
	
	public Color getHoveredColor()
	{
		return this.HoverdColor;
	}
	
	public void setHoveredBorderStyle(BorderStyles borderstyle)
	{
		this.HoveredBorderStyle = borderstyle;
	}
	
	public BorderStyles getHoveredBorderStyle()
	{
		return this.HoveredBorderStyle;
	}
	
	public void setHoveredBorderColor(Color hoveredbordercolor)
	{
			this.HoveredBorderColor = hoveredbordercolor;
	}
	
	public Color getHoveredBorderColor()
	{
		return this.HoveredBorderColor;
	}
	
	public boolean getIsHovered()
	{
		return this.isHoverd;
	}
	
	
}
