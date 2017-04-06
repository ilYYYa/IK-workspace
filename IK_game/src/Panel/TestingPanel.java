package Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import Button.TestingButton;

public class TestingPanel extends GlobalPanel
{
	int rr = 255;
	
	public TestingPanel(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		this.addObjectForDraw(new TestingButton(10,10,w-20,50));
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
	public void onMousePress(MouseEvent event)
	{
		rr = 100;
		super.onMousePress(event);
	}
}
