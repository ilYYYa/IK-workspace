package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Game.Game;
import Window.DoubleBuffer;

public class Scene_Logo extends GlobalScene
{
	private int timer = 0;
	
	public Scene_Logo()
	{
		super();
	}

	@Override
	public void draw(Graphics g)
	{
		int c = (int)((double)timer/180d*255d);
		g.setColor(new Color(c,c,c));
		g.fillRect(0, 0, (int)realWidth(), (int)realHeight());
		g.setColor(new Color(255-c,255-c,255-c));
		g.drawString("LOGO MFK!!! (missed) | " + timer, 100, 100);
	}

	@Override
	public void logic()
	{
		timer++;
		if(timer >= 180)
		{
			Game.theGame.theDoubleBuffer.setScene(new Scene_Load());
		}
	}
	
	@Override
	public void onKeyPress(KeyEvent event)
	{
		if(event.getKeyCode() == 32 || event.getKeyCode() == 10) Game.theGame.theDoubleBuffer.setScene(new Scene_Load());
		super.onKeyPress(event);
	}
}
