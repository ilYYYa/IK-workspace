package Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import Obj.DrawbleObject;
import Resources.TextureLoader;
import util.TextureEditor;

public class GlobalButton extends DrawbleObject
{
	public Color bgColor = new Color(200,200,200);
	public Color borderColor = new Color(255,255,255);
	public Color OnButtonMouseBgColor = new Color(180,180,180);
	public Color ColorDali = new Color(0,255,0);
	public Color OnNonActiveButton = new Color(150,150,150);
	
	public String Text = "";
	
	public int mouseAction = 0;
	
	public boolean ButtonActive = true;

	public GlobalButton(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(parent);
		posX = x;
		posY = y;
		width = w;
		height = h;
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
	    
	    if(!ButtonActive) g.setColor(OnNonActiveButton);
	    
		g.fillRect((int)posXOnScreen(), (int)posYOnScreen(), (int)realWidth(), (int)realHeight());
		g.setColor(borderColor);
		g.drawRect((int)posXOnScreen(), (int)posYOnScreen(), (int)realWidth(), (int)realHeight());

		Image buff = TextureLoader.getTextureByName("!002" + this.Text + "&" + TextureEditor.getStringWidthByHeight(this.Text, (int)this.realHeight()/2) + "&" + ((int)this.realHeight()/2));
		g.drawImage(buff, (int)posXOnScreen() + (int)this.realWidth()/2 - buff.getWidth(null)/2, (int)posYOnScreen() + (int)this.realHeight()/4, null);
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
		if(ButtonActive) mouseAction = 1;
		super.onMouseMove(event);
	}
	
	@Override
	public void onMouseMoveNotOnYou(MouseEvent event)
	{
		mouseAction = 0;
		super.onMouseMoveNotOnYou(event);
	}
	
	@Override
	public void onMousePress(MouseEvent event)
	{
		if(ButtonActive) mouseAction = 2;
	    super.onMousePress(event);
	}
	
	@Override
	public void onMouseRelease(MouseEvent event)
	{
		mouseAction = 0;
		super.onMouseRelease(event);
	}

	@Override
	public void logic(){}
}
