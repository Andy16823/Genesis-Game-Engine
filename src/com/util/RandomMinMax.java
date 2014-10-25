package com.util;

import java.util.Random;

public class RandomMinMax {

	public static int getNextValue(Random rnd,int Min, int Max)
	{
		int difference = Max - Min;
		return rnd.nextInt(difference) + Min;
	}
	
}
