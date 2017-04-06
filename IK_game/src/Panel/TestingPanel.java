package Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class TestingPanel extends GlobalPanel
{
	int rr = 255;
	
	public TestingPanel(int x, int y, int w, int h)
	{
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g)
	{
		if(rr < 255) rr++;
		g.setColor(new Color(rr,0,0));
		g.fillRect((int)posX + 5, (int)posY + 5, (int)width - 10, (int)height - 10);
		super.draw(g);
	}
	
	@Override
	public void onMouseClick(MouseEvent event)
	{
		rr = 100;
		super.onMouseClick(event);
	}
}
