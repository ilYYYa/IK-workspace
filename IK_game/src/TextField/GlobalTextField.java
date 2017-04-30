package TextField;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import Obj.DrawbleObject;
import Resources.TextureLoader;

public class GlobalTextField extends DrawbleObject
{
	private ArrayList<String> textForDraw = new ArrayList<String>();
	private int textSize = 16;

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
	
	public void setTextSize(int textSize)
	{
		this.textSize = textSize;
	}
	public int getTextSize()
	{
		return this.textSize;
	}
	
	@Override
	public void logic()
	{
		
	}

	@Override
	public void draw(Graphics g)
	{
		for(int i = 0; i < textForDraw.size() && (i+1)*this.textSize < this.realHeight(); i++)
		{
			Image buff = TextureLoader.getTextureByName("!002" + textForDraw.get(i) + "&" + (int)this.realWidth() + "&" + this.textSize);
			g.drawImage(buff, (int)this.posXOnScreen(), (int)this.posYOnScreen() + i*this.textSize, null);
		}
	}
}

























