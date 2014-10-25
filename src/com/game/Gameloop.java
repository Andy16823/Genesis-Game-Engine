package com.game;

public class Gameloop extends Thread{

	public long Wait;
	public Game Game;
	private boolean run;
	
	public Gameloop(Game g, long Wait)
	{
		this.Game = g;
		this.Wait = Wait;
	}
	
	public void GameStop()
	{
		this.run = false;
	}
	
	@Override
	public void run()
	{
		this.run = true;
		while(run == true)
		{
			try {
				this.sleep(Wait);
				this.Game.update();
				this.Game.invalidate();
				this.Game.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
