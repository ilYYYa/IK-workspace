package Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import Obj.DrawbleObject;

public class GlobalButton extends DrawbleObject
{
	public Color bgColor = new Color(200,200,200);
	public Color borderColor = new Color(255,255,255);
	public Color textColor = new Color(0,0,0);
	public Color OnButtonMouseBgColor = new Color(180,180,180);
	public Color ColorDali = new Color(0,255,0);
	
	public String Text = "";
	
	public int mouseAction = 0;

	public GlobalButton(double x, double y, double w, double h)
	{
		localPosX = x; localPosY = y; width = w; height = h;
	}
	
	@Override
	public void draw(Graphics g)
	{
	    switch (mouseAction)
	    {
	    case 0: g.setColor(bgColor); break;
	    case 1: g.setColor(OnButtonMouseBgColor); break;
	    case 2: g.setColor(ColorDali); break;
	    }
		g.fillRect((int)posX, (int)posY, (int)width, (int)height);
		g.setColor(borderColor);
		g.drawRect((int)posX, (int)posY, (int)width, (int)height);
		g.setColor(textColor);
		g.drawString(Text, (int)posX + 5, (int)posY + (int)height/2);
	}
	
	public GlobalButton setText(String text)
	{
		Text = text;
		return this;
	}
	
	public GlobalButton setBorderColor(int r, int g, int b)
	{
		borderColor = new Color(r,g,b);
		return this;
	}

	public GlobalButton setTextColor(int r, int g, int b)
	{
		textColor = new Color(r,g,b);
		return this;
	}

	public GlobalButton setBackgroundColor(int r, int g, int b)
	{
		bgColor = new Color(r,g,b);
		return this;
	}

	public GlobalButton setOnButtonMouseBgColor(int r, int g, int b)
	{
		OnButtonMouseBgColor = new Color(r,g,b);
		return this;
	}
	
	@Override
	public void onMouseMove(MouseEvent event)
	{
		mouseAction = 1;
		super.onMouseMove(event);
	}
	
	@Override
	public void onMouseMoveNotOnYou(MouseEvent event)
	{
		mouseAction = 0;
		super.onMouseMove(event);
	}
	
	@Override
	public void onMousePress(MouseEvent event)
	{
		mouseAction = 2;
	    super.onMousePress(event);
	}
	
	@Override
	public void onMouseRelease(MouseEvent event)
	{
		mouseAction = 0;
		super.onMouseRelease(event);
	}
}
