package InputLine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Obj.DrawbleObject;

public class GlobalInputLine extends DrawbleObject
{
	private String str = "";
	private String strD = "";
	private int t = 0;
	
	private int maxSymbols = 16;

	public GlobalInputLine(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(parent);
		posX = x;
		posY = y;
		width = w;
		height = h;
	}
	
	public void setMaxSymbols(int m)
	{
		this.maxSymbols = m;
	}

	public void setText(String str)
	{
		this.str = str;
	}
	
	public String getText()
	{
		return this.str;
	}
	

	@Override
	public void logic()
	{
		t++;
		if(t > 30) t = -30;
		
		if(this.focusOnMe())
		{
			if(t >= 0) strD = str + "|";
			else strD = str;
		}
		else strD = str;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)posXOnScreen(), (int)posYOnScreen(), (int)realWidth(), (int)realHeight());
		g.setColor(Color.black);
		g.drawRect((int)posXOnScreen(), (int)posYOnScreen(), (int)realWidth(), (int)realHeight());
		g.drawRect((int)posXOnScreen()+1, (int)posYOnScreen()+1, (int)realWidth()-2, (int)realHeight()-2);
		g.drawRect((int)posXOnScreen()+2, (int)posYOnScreen()+2, (int)realWidth()-4, (int)realHeight()-4);
		g.drawString(strD, (int)posXOnScreen() + 5, (int)posYOnScreen() + (int)realHeight()/2);
	}

	@Override
	public void onKeyPress(KeyEvent e)
	{
		if(e.getKeyCode() >= 48 && e.getKeyCode() <= 90 && str.length() < maxSymbols) str += e.getKeyChar();
		if(e.getKeyCode() == 8 && str.length() > 0) str = str.substring(0, str.length()-1);
	}
}
