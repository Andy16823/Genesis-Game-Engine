package com.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.physik.collider.Collider;
import com.physik.collider.CollisionInformation;
import com.types.gameobject;
import com.types.sprite;
import com.ui.UIManager;
import com.vector.vector2;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Game extends JPanel {

	public enum ColliderTypes{ VIEW_COLLIDER, COMPLETTE_COLLIDER, BOUND_COLLIDER, BOTTOM_COLLIDER};
	
	/**
	 *  public Variabels
	 */
	private com.types.SceneManager SceneManager;
	private com.types.sprite SelectedSprite;
	private BufferedImage MapBuffer;
	private int Key;
	private boolean AutoGravity;
	private ColliderTypes ColliderType;
	private Vector<UIManager> UIManagers;
	
	
	/**
	 * Private Variables
	 */
	private com.game.events.OnPaintListener OnPaintListener;
	private com.game.events.OnUpdateListener OnUpdateListener;
	private com.game.events.OnCollisionListener OnCollisionListener;
	private Gameloop gameloop;
	private long LoopTime;
	private boolean LeftMouseClicked;
	private boolean RightMouseClicked;
	private boolean MiddleMouseClicked;
	private vector2 MousePosition;
	private BufferedImage BackgroundImage;
	private KeyEvent KeyEvent;
	
	public void setSceneManager(com.types.SceneManager Manager)
	{
		this.SceneManager = Manager;
	}
	
	public void setSlectedSprite(sprite SelectedSprite)
	{
		this.SelectedSprite = SelectedSprite;
	}
	
	public void setMapBuffer(BufferedImage Image)
	{
		this.MapBuffer = Image;
	}
	
	public void setAutoGravity(boolean AutoGravity)
	{
		this.AutoGravity = AutoGravity;
	}
	
	public void setColliderType(ColliderTypes ColliderType)
	{
		this.ColliderType = ColliderType;
	}
	
	public void addUIManager(UIManager Manager)
	{
		this.UIManagers.add(Manager);
	}
	
	public com.types.SceneManager getSceneManager()
	{
		return this.SceneManager;
	}
	
	public sprite getSelectedSprite()
	{
		return this.SelectedSprite;
	}
	
	public BufferedImage getMapBuffer()
	{
		return this.MapBuffer;
	}
	
	public int getInput()
	{
		return this.Key;
	}
	
	public void DisposeInput()
	{
		this.Key = 0;
	}
	
	public KeyEvent getKeyEvent()
	{
		return this.KeyEvent;
	}
	
	public boolean getAutoGravity()
	{
		return this.AutoGravity; 
	}
	
	public ColliderTypes getColliderType()
	{
		return this.ColliderType;
	}
	
	public UIManager getUIManager(String Name)
	{
		UIManager returnvalue = null;
		for(UIManager var : this.UIManagers)
		{
			if(var.getName().equals(Name))
			{
				returnvalue = var;
			}
		}
		return returnvalue;
	}
	
	/**
	 * Game Methods
	 */
	
	// Constructor
    public Game(long Looptime) {
    	 this.UIManagers = new Vector<com.ui.UIManager>();
    	 this.addKeyListener(kl);
    	 this.addMouseListener(ml);
    	 this.addMouseMotionListener(mml);
    	 this.LoopTime = Looptime;
    	 this.gameloop = new Gameloop(this, this.LoopTime);
    	 this.requestFocus();
     }
	
     // Paint event
	 @Override
     public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Paint Background
		if(this.BackgroundImage != null)
		{
			g.drawImage(BackgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		
		 // Pant Mapbuffer
		 if(this.MapBuffer != null)
		 {
			 g.drawImage(this.MapBuffer, this.SceneManager.GetScene().getLocation().getX(), this.SceneManager.GetScene().getLocation().getY(), this.SceneManager.GetScene().getSize().getWidth(), this.SceneManager.GetScene().getSize().getHeight(), this);
		 }
		 
		 // Paint GameObjects
		 if(this.SceneManager.GetGameObjects() != null)
		 {
			 for(gameobject go : this.SceneManager.GetGameObjects())
			 {
				 if(go.getRenderMode() == gameobject.RenderModes.ON_GAME_PAINT)
				 {
					 if(go.getLocation().getX() > 0 && go.getLocation().getX() < this.getWidth() && go.getLocation().getY() > 0 && go.getLocation().getY() < this.getHeight())
					 {
						 g.drawImage(go.getTexture(), go.getLocation().getX(), go.getLocation().getY(), go.getSize().getWidth(), go.getSize().getHeight(), this);
					 }
				 }
			 }
		 }
		 
		 // Paint Sprite
		 if(this.SelectedSprite.getSelectedTexture() != null)
		 {
			 g.drawImage(this.SelectedSprite.getSelectedTexture(), this.SelectedSprite.getLocation().getX(), this.SelectedSprite.getLocation().getY(), this.SelectedSprite.getSize().getWidth(), this.SelectedSprite.getSize().getHeight(), this);
		 }
		 // Paint Event
		 if(this.OnPaintListener != null)
		 {
			 this.OnPaintListener.OnPaint(this, g);
		 }
		 
		 // Paint UI Managers
		 for(UIManager manager : this.UIManagers)
		 {
			 if(manager.getEnabled() == true)
			 {
				 manager.Paint(g);
			 }
		 }
	 }
	 
	 // update method
	 public void update()
	 {
		 if(this.AutoGravity == true)
		 {
			 Collider collider = new Collider();
			 CollisionInformation collinfo = null;
			 
			 if(this.ColliderType == ColliderTypes.COMPLETTE_COLLIDER)
			 {
				collinfo = collider.GetCollision(this.SceneManager.GetGameObjects(), this.SelectedSprite);
			 }
			 else if(this.ColliderType == ColliderTypes.VIEW_COLLIDER)
			 {
				 collinfo = collider.GetViewCollisions(this.SceneManager.GetGameObjects(), this.SelectedSprite, this.getWidth(), this.getHeight());
			 }
			 else if(this.ColliderType == ColliderTypes.BOUND_COLLIDER)
			 {
				 collinfo = collider.GetBoundCollider(this.SceneManager.GetGameObjects(), this.SelectedSprite);
			 }
			 else if(this.ColliderType == ColliderTypes.BOTTOM_COLLIDER)
			 {
				 collinfo = collider.GetBottomCollisions(this.SceneManager.GetGameObjects(), this.SelectedSprite);
			 }
			 
			 if(collinfo.getCollision() == true && this.SelectedSprite.getGravity().getEnabled() == true)
			 {
				 if(this.OnCollisionListener != null)
				 {
					 this.OnCollisionListener.OnCollision(this, collinfo);
				 }
			 }
			 else
			 {
				 this.SelectedSprite.getLocation().setX(this.SelectedSprite.getLocation().getX() - this.SelectedSprite.getGravity().getHorizontalValue());
				 this.SelectedSprite.getLocation().setY(this.SelectedSprite.getLocation().getY() + this.SelectedSprite.getGravity().getVerticalValue());
			 }
		 }
		 
		 if(this.OnUpdateListener != null)
		 {
			 this.OnUpdateListener.OnUpdate(this);
		 }
	 }
	 
	 // starts the game loop
	 public void StartGame()
	 {
		 this.gameloop = new Gameloop(this, this.LoopTime);
		 this.gameloop.start();
	 }
	 
	 // stops the game loop
	 public void StopGame()
	 {
		 this.gameloop.GameStop();
	 }
	 
	 // gets the MouseLeft State
	 public boolean isMouseLeftDown()
	 {
		 return this.LeftMouseClicked;
	 }
	 
	 // gets the MouseRight State
	 public boolean isMouseRightDown()
	 {
		 return this.RightMouseClicked;
	 }
	 
	 // gets the MouseMiddleState
	 public boolean isMouseMiddleDown()
	 {
		 return this.MiddleMouseClicked;
	 }
	 
	 // gets the vector of the Mouse Position
	 public vector2 MousePosition()
	 {
		 return this.MousePosition;
	 }
	
	 public void SetBackgroundImage(BufferedImage Image)
	 {
		 this.BackgroundImage = Image;
	 }
	 
	 /**
	  * Own Event Methods	 
	  */
	 
	 // sets the Update listener
	 public void addOnUpdateListener(com.game.events.OnUpdateListener listener)
	 {
		 this.OnUpdateListener = listener;
	 }
	 
	 // sets the Paint Listener
	 public void addOnPaintListener(com.game.events.OnPaintListener listener)
	 {
		 this.OnPaintListener = listener;
	 }
	 
	 // sets the Collisionlistener
	 public void addOnCollisionListener(com.game.events.OnCollisionListener listener)
	 {
		 this.OnCollisionListener = listener;
	 }
	 
	 /**
	  * Events
	  */
	 
	 // Key listener	 
	 public KeyListener kl = new KeyListener(){

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			Game g = (Game) arg0.getSource();
			g.Key = arg0.getKeyCode();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			Game g = (Game) arg0.getSource();
			g.Key = 0;
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	
	 };
	 
	 // Maus Listener
	 public MouseListener ml = new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Game game = (Game) arg0.getSource();
			for(UIManager manager : game.UIManagers)
			{
				if(manager.getEnabled() == true)
				{
					manager.Click(arg0);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Game game = (Game) arg0.getSource();
			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
				game.LeftMouseClicked = true;
			}
			if(arg0.getButton() == MouseEvent.BUTTON2)
			{
				game.RightMouseClicked = true;
			}
			if(arg0.getButton() == MouseEvent.BUTTON3)
			{
				game.MiddleMouseClicked = true;
			}
			game.MousePosition = new vector2(arg0.getPoint().x, arg0.getPoint().y);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Game game = (Game) arg0.getSource();
			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
				game.LeftMouseClicked = false;
			}
			if(arg0.getButton() == MouseEvent.BUTTON2)
			{
				game.RightMouseClicked = false;
			}
			if(arg0.getButton() == MouseEvent.BUTTON3)
			{
				game.MiddleMouseClicked = false;
			}
			
			for(UIManager manager : game.UIManagers)
			{
				if(manager.getEnabled() == true)
				{
					manager.MouseUp(arg0);
				}
			}
			
		}
		 
	 };
	 
	 public MouseMotionListener mml = new MouseMotionListener()
	 {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Game game = (Game) arg0.getSource();
			for(UIManager manager : game.UIManagers)
			{
				if(manager.getEnabled()==true)
				{
					manager.MouseMove(arg0);
				}
			}
			
		}
		 
	 };
}


