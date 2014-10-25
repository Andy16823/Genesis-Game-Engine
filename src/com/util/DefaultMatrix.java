package com.util;

import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

public class DefaultMatrix {

	/**
	 * Erstellt eine Liste mit Zufällig generierten Rechtecken.
	 * @param MinValue	Die Minimale Anzahl der Rechtecke
	 * @param MaxValue Die Maximale Anzahl der Rechtecke
	 * @param TopValue Der Obere Wert
	 * @param BottomValue Der Untere Wert
	 * @param Width Die Breite der Rechtecke
	 * @param Height Die Höhe der Rechtecke
	 * @param MinXOffset Der Minimale Abstand zum vorherrigen Rechteck 
	 * @param MaxXOffset Der Maximale Abstand zum vorherrigen Rechteck
	 * @return Vector<Rectangle>
	 */
	public static Vector<Rectangle> BuildMatrix(int MinValue, int MaxValue, int TopValue, int BottomValue, int Width, int Height, int MinXOffset, int MaxXOffset)
	{
		Vector<Rectangle> list = new Vector<Rectangle>();
		Random RndX = new Random();
		Random RndY = new Random();
		Random RndVal = new Random();
		
		int LastWidht = 0;
		int RectValue = com.util.RandomMinMax.getNextValue(RndVal, MinValue, MaxValue);
		
		for( int i = 0 ; i < RectValue ; i++ )
		{
			int X = com.util.RandomMinMax.getNextValue(RndX, LastWidht + MinXOffset, LastWidht + MaxXOffset);
			int Y = com.util.RandomMinMax.getNextValue(RndY, TopValue, BottomValue);
			Rectangle rect = new Rectangle(X, Y, Width, Height);
			list.add(rect);
			
			LastWidht = X + Width;
		}
		
		return list;	
		
	}
	
}
