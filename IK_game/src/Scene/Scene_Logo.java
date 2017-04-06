package Scene;

import java.awt.Graphics;

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
		g.drawString("LOGO MFK!!! (missed) | " + timer, 100, 100);
		timer++;
		if(timer >= 180)
		{
			DoubleBuffer.setScene(null);
		}
	}
}
