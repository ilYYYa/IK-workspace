package TextField;

import java.awt.Graphics;

import Obj.DrawbleObject;

public class GloblaTextField extends DrawbleObject
{
	private String textForDraw = "";

	public GloblaTextField(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(parent);
		posX = x;
		posY = y;
		width = w;
		height = h;
	}

	public void setTextForDraw(String str)
	{
		this.textForDraw = str;
	}
	
	public String getTextForDraw()
	{
		return this.textForDraw;
	}
	
	@Override
	public void logic()
	{
		
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawString(textForDraw, (int)posXOnScreen() + 5, (int)posYOnScreen() + (int)realHeight()/2);
	}

}
