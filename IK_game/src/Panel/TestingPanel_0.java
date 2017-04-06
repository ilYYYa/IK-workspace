package Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import Scene.GlobalScene;

public class TestingPanel_0 extends GlobalPanel
{
	int bb = 255;

	public TestingPanel_0(int x, int y, int w, int h, GlobalScene scene_parent)
	{
		super(x, y, w, h, scene_parent);
	}

	@Override
	public void draw(Graphics g)
	{
		if(bb < 255) bb++;
		g.setColor(new Color(0,bb,0));
		g.fillRect((int)posX + 5, (int)posY + 5, (int)width - 10, (int)height - 10);
		super.draw(g);
	}
	
	@Override
	public void onMousePress(MouseEvent event)
	{
		bb = 100;
		super.onMousePress(event);
	}

}