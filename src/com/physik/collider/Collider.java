package com.physik.collider;

import java.awt.Point;
import java.util.Vector;

import com.types.gameobject;
import com.types.sprite;

public class Collider {

	public Collider()
	{
		
	}
	
	public CollisionInformation GetCollision(Vector<gameobject> GameObjects, sprite Sprite)
	{
		CollisionInformation returnvalue = new CollisionInformation();
		
		int X = Sprite.getBounds().x;
		int Y = Sprite.getBounds().y;
		int Width = Sprite.getBounds().width;
		int Height = Sprite.getBounds().height;
		
		for( gameobject go : GameObjects)
		{
			// Top and Bottom
			if(returnvalue.getCollision() == false)
			{
				for(int i = X ; i < X + Width ; i++)
				{
					if(go.getBounds().contains(i, Y))
					{
						returnvalue.setCollision(true);
						returnvalue.setGameObject(go);
					}
					else if(go.getBounds().contains(i, Y + Height))
					{
						returnvalue.setCollision(true);
						returnvalue.setGameObject(go);
					}
				}
			}
			// Right and Left
			if(returnvalue.getCollision() == false)
			{
				for(int i = Y; i < Y + Height ; i++)
				{
					if(go.getBounds().contains(X + Width, i))
					{
						returnvalue.setCollision(true);
						returnvalue.setGameObject(go);
					}
					else if(go.getBounds().contains(X, i))
					{
						returnvalue.setCollision(true);
						returnvalue.setGameObject(go);
					}
				}
			}	
		}

		return returnvalue;
	}
	
	public CollisionInformation GetViewCollisions(Vector<gameobject> GameObjects, sprite Sprite, int Width, int Height)
	{
		CollisionInformation returnvalue = new CollisionInformation();
		
		int X = Sprite.getBounds().x;
		int Y = Sprite.getBounds().y;
		int SWidth = Sprite.getBounds().width;
		int SHeight = Sprite.getBounds().height;
		
		
		for(gameobject go : GameObjects)
		{
			if(returnvalue.getCollision() == true)
			{
				break;
			}
			if(returnvalue.getCollision() == false)
			{
				if(go.getLocation().getX() > 0 && go.getLocation().getX() < Width && go.getLocation().getY() > 0 && go.getLocation().getY() < Height)
				{
					for( int i = X ; i < X + SWidth ; i++)
					{
						if(go.getBounds().contains(i, Y))
						{
							returnvalue.setCollision(true);
							returnvalue.setGameObject(go);
							break;
						}
						else if(go.getBounds().contains(i, Y + SHeight))
						{
							returnvalue.setCollision(true);
							returnvalue.setGameObject(go);
							break;
						}
					}
				}
			}
			if(returnvalue.getCollision() == false)
			{
				if(go.getLocation().getX() > 0 && go.getLocation().getX() < Width && go.getLocation().getY() > 0 && go.getLocation().getY() < Height)
				{
					for( int i = Y ; i < Y + SHeight ; i++ )
					{
						if(go.getBounds().contains(X, i))
						{
							returnvalue.setCollision(true);
							returnvalue.setGameObject(go);
							break;
						}
						else if(go.getBounds().contains(X + SWidth, i))
						{
							returnvalue.setCollision(true);
							returnvalue.setGameObject(go);
							break;
						}
					}
				}
			}

		}
		return returnvalue;		
	}
	
	public CollisionInformation GetBoundCollider(Vector<gameobject> GameObjects, sprite Sprite)
	{
		CollisionInformation returnvalue = new CollisionInformation();
		Point OL = new Point(Sprite.getLocation().getX(), Sprite.getLocation().getY());
		Point OR = new Point(Sprite.getLocation().getX() + Sprite.getSize().getWidth(), Sprite.getLocation().getY());
		Point UL = new Point(Sprite.getLocation().getX(), Sprite.getLocation().getY() + Sprite.getSize().getHeight());
		Point UR = new Point(Sprite.getLocation().getX() + Sprite.getSize().getWidth(), Sprite.getLocation().getY() + Sprite.getSize().getHeight());
		
		for(gameobject go : GameObjects)
		{
			if(go.getBounds().contains(UR) || go.getBounds().contains(UL) || go.getBounds().contains(OR) || go.getBounds().contains(OL))
			{
				returnvalue.setCollision(true);
				returnvalue.setGameObject(go);
				break;
			}
		}
		
		return returnvalue;
	}
	
	public CollisionInformation GetBottomCollisions(Vector<gameobject> GameObjects, sprite Sprite)
	{
		CollisionInformation returnvalue = new CollisionInformation();
		
		// Start Punkte
		int Start = Sprite.getBounds().x;
		int Ende = Sprite.getBounds().x + Sprite.getSize().getWidth();
		for(gameobject var : GameObjects)
		{
			for(int i = Start; i < Ende; i++)
			{
				if(var.getBounds().contains(i,Sprite.getBounds().y + Sprite.getSize().getHeight()))
				{
					returnvalue.setCollision(true);
					returnvalue.setGameObject(var);
					return returnvalue;
				}
			}
		}
		
		// Gibt ein Leeres Element zurück
		returnvalue.setCollision(false);
		returnvalue.setGameObject(null);
		return returnvalue;
		
	}
	
}
