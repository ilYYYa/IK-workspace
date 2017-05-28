package Button;

import java.awt.Color;
import java.awt.event.MouseEvent;

import Obj.DrawbleObject;
import Resources.Texture;
import Resources.TextureLoader;
import Window.MainWindow;
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
	public void draw(MainWindow g)
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
		
		int TW = TextureEditor.getStringWidthByHeight(this.Text, (int)this.realHeight()/2);
		g.drawText(this.Text, (int)posXOnScreen() + (int)this.realWidth()/2 - TW/4, (int)posYOnScreen() + (int)this.realHeight()/4, TW, ((int)this.realHeight()/2));
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
	public void onMouseMove(int x, int y, int dx, int dy)
	{
		if(ButtonActive) mouseAction = 1;
		super.onMouseMove(x,y,dx,dy);
	}
	
	@Override
	public void onMouseMoveNotOnYou(int x, int y)
	{
		mouseAction = 0;
		super.onMouseMoveNotOnYou(x, y);
	}
	
	@Override
	public void onMousePress(int x, int y, int btn)
	{
		if(ButtonActive) mouseAction = 2;
	    super.onMousePress(x, y, btn);
	}
	
	@Override
	public void onMouseRelease(int x, int y, int btn)
	{
		mouseAction = 0;
		super.onMouseRelease(x,y,btn);
	}

	@Override
	public void logic(){}
}
