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

public class Panel extends Control{
	
	public Panel(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
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
		g2d.setColor(this.getBackgroundColor());
		g2d.fillRect(0, 0, this.getSize().getWidth(), this.getSize().getHeight());
			
		// Draw Image
		if(this.getImageStyle() == ImageStyles.NORMAL_DRAWING)
		{
			BufferedImage subbmp = this.getBackgroundImage().getSubimage(0, 0, this.getSize().getWidth(), this.getSize().getHeight());
			g2d.drawImage(subbmp, 0, 0, this.getSize().getWidth(), this.getSize().getHeight(), null);
		}
		else if(this.getImageStyle() == ImageStyles.STRECH_DRAWING)
		{
			g2d.drawImage(this.getBackgroundImage(), 0, 0, this.getSize().getWidth(), this.getSize().getHeight(), null);
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
				
		// After Paint Event
		if(this.getOnPaintLister() != null)
		{
			this.getOnPaintLister().AfterDrawing(this, g);
		}
		
		// Draw Control
		g.drawImage(bmp, this.getLocation().getX(), this.getLocation().getY(), this.getSize().getWidth(), this.getSize().getHeight(), null);
	}
	
}
