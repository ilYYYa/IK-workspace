package TextField;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Obj.DrawbleObject;

public class GlobalTextField extends DrawbleObject
{
	private ArrayList<String> textForDraw = new ArrayList<String>();
	private Color color = new Color(0,0,0);

	public GlobalTextField(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(parent);
		posX = x;
		posY = y;
		width = w;
		height = h;
	}

	public void addTextForDraw(String str)
	{
		this.textForDraw.add(str);
	}
	
	public String[] getTextForDraw()
	{
		String[] buff = new String[this.textForDraw.size()];
		return this.textForDraw.toArray(buff);
	}

	public void setTextForDraw(String string)
	{
		this.textForDraw.clear();
		this.textForDraw.add(string);
	}
	
	public void setColor(Color c)
	{
		color = c;
	}
	public void setColor(int r, int g, int b)
	{
		this.setColor(new Color(r,g,b));
	}
	
	@Override
	public void logic()
	{
		
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		for(int i = 0; i < this.textForDraw.size(); i++)
		{
			g.drawString(textForDraw.get(i), (int)posXOnScreen() + 5, (int)posYOnScreen() + i*15 + 15);
		}
	}

}
