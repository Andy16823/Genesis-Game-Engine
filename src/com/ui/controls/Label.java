package com.ui.controls;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.game.Game;
import com.ui.Control;
import com.ui.Control.BorderStyles;
import com.ui.Control.ImageStyles;
import com.ui.Control.TextStyles;

public class Label extends Control {

	public enum BackgroundStyles{TRANSPARENT_COLOR, BACKGROUND_COLOR};
	public BackgroundStyles BackgroundStyle;
	
	public Label(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	public void setBackgroundStyle(BackgroundStyles style)
	{
		this.BackgroundStyle = style;
	}
	
	public BackgroundStyles getBackgroundStyle()
	{
		return this.BackgroundStyle;
	}
	
	@Override
	public void Paint(Graphics g)
	{
		// Bevore Paint Event
		super.Paint(g);
		if(this.getOnPaintLister() != null)
		{
			this.getOnPaintLister().BevorePaint(this, g);
		}

		// Control Image
		BufferedImage bmp = new BufferedImage(this.getSize().getWidth(), this.getSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bmp.createGraphics();	
		
		// Draw Background
		if(this.BackgroundStyle == BackgroundStyles.BACKGROUND_COLOR)
		{
			g2d.setColor(this.getBackgroundColor());
			g2d.fillRect(0, 0, this.getSize().getWidth(), this.getSize().getHeight());
		}
		
		// Draw Border
		if(this.getBorderStyle() == BorderStyles.NORMAL_STYLE)
		{
			g2d.setColor(this.getBorderColor());
			g2d.drawRect(0, 0, this.getSize().getWidth() -1, this.getSize().getHeight() -1);
		}
		else if(this.getBorderStyle() == BorderStyles.ROUNDED_STYLE)
		{
			g2d.setColor(this.getBorderColor());
			g2d.drawRoundRect(5, 5, this.getSize().getWidth() - 10, this.getSize().getHeight() - 10, 10, 10);
		}
		
		// Draw Text
		if(this.getTextStyle() == TextStyles.CENTER_ALLIGN)
		{
			g2d.setFont(this.getFont());
			g2d.setColor(this.getForeColor());
			FontMetrics m = g2d.getFontMetrics(this.getFont());
			int TxtLength = m.stringWidth(this.getText());
			int TxtHeight = m.getHeight();
			g2d.drawString(this.getText(), (this.getSize().getWidth() / 2) - (TxtLength / 2), (this.getSize().getHeight() / 2) + (m.getHeight() / 2));
		}
		else if(this.getTextStyle() == TextStyles.LEFT_ALLIGN)
		{
			g2d.setFont(this.getFont());
			g2d.setColor(this.getForeColor());
			FontMetrics m = g2d.getFontMetrics(this.getFont());
			int TxtHeight = m.getHeight();
			g2d.drawString(this.getText(), 10, this.getSize().getHeight() / 2);
		}
		
		// After Paint Event
		if(this.getOnPaintLister() != null)
		{
			this.getOnPaintLister().AfterDrawing(this, g);
		}
		
		// Draw Control
		g.drawImage(bmp, this.getLocation().getX(), this.getLocation().getY(), this.getSize().getWidth(), this.getSize().getHeight(), null);
	}
	
}
