package com.ui.events;

import java.awt.Graphics;

public interface OnPaintListener {

	// Calls bevore Drawing Control
	void BevorePaint(Object sender, Graphics g);
	
	// Calls ends Drawing
	void AfterDrawing(Object sender, Graphics g);
	
}
