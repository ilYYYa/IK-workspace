package InputLine;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import Obj.DrawbleObject;
import Resources.Texture;
import Resources.TextureLoader;
import Window.MainWindow;

public class GlobalInputLine extends DrawbleObject
{
	private String str = "";
	private String strD = "";
	private int t = 0;
	
	private int maxSymbols = 16;
	
	private boolean acceptSpace = true;

	public GlobalInputLine(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(parent);
		posX = x;
		posY = y;
		width = w;
		height = h;
	}
	
	public void setAcceptinSpace(boolean acc)
	{
		acceptSpace = acc;
	}
	public boolean getSpaceAccepting()
	{
		return acceptSpace;
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
	public void draw(MainWindow g)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)posXOnScreen(), (int)posYOnScreen(), (int)realWidth(), (int)realHeight());
		g.setColor(Color.black);
		g.drawRect((int)posXOnScreen(), (int)posYOnScreen(), (int)realWidth(), (int)realHeight());
		g.drawRect((int)posXOnScreen()+1, (int)posYOnScreen()+1, (int)realWidth()-2, (int)realHeight()-2);
		g.drawRect((int)posXOnScreen()+2, (int)posYOnScreen()+2, (int)realWidth()-4, (int)realHeight()-4);

		Texture buff = TextureLoader.getTextureByName("!002" + this.strD + "&" + ((int)this.realWidth()-10) + "&" + ((int)this.realHeight()/2));
		g.drawTexture(buff, (int)posXOnScreen() + 5, (int)posYOnScreen() + (int)this.realHeight()/4);
	}

	@Override
	public void onKeyPress(int code, String name)
	{
		if(name.length() < 1) return;

		if(code == Keyboard.KEY_BACK && str.length() > 0) str = str.substring(0, str.length()-1);
		else if(code == Keyboard.KEY_SPACE && this.acceptSpace && str.length() < maxSymbols) str += " ";
		else if(str.length() < maxSymbols) str += name.substring(0, 1);
	}
}






















