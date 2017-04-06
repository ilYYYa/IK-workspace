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
	
	public String Text = "";

	public GlobalButton(double x, double y, double w, double h)
	{
		localPosX = x; localPosY = y; width = w; height = h;
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(bgColor);
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
		//Need kaifolom's function!!!
		super.onMouseMove(event);
	}
}
