package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Window.DoubleBuffer;

public class Scene_Logo extends GlobalScene
{
	private int timer = 0;
	
	public Scene_Logo(double w, double h)
	{
		super(w, h);
	}

	@Override
	public void draw(Graphics g)
	{
		int c = (int)((double)timer/180d*255d);
		g.setColor(new Color(c,c,c));
		g.fillRect(0, 0, (int)width, (int)height);
		g.setColor(new Color(255-c,255-c,255-c));
		g.drawString("LOGO MFK!!! (missed) | " + timer, 100, 100);
		timer++;
		if(timer >= 180)
		{
			DoubleBuffer.setScene(new Scene_Load(width, height));
		}
	}
	
	@Override
	public void onKeyPress(KeyEvent event)
	{
		System.out.println("text");
		if(event.getKeyCode() == 32 || event.getKeyCode() == 10) DoubleBuffer.setScene(new Scene_Load(width, height));
		super.onKeyPress(event);
	}
}
