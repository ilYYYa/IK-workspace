package Button;

import Obj.DrawbleObject;

public class GButton_ONOFF extends GlobalButton
{
	public boolean ON = false;
	public String textFotONMode = "ON";
	public String textFotOFFMode = "OFF";
	
	public GButton_ONOFF(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
	}
	
	public void setTextForONMode(String txt)
	{
		textFotONMode = txt;
	}
	public void setTextForOFFMode(String txt)
	{
		textFotOFFMode = txt;
	}

	public void turnOff()
	{
		ON = false;
	}
	public void turnON()
	{
		ON = true;
	}
	
	@Override
	public String getText()
	{
		return super.getText() + ": " + (ON ? textFotONMode : textFotOFFMode);
	}
	
	@Override
	public void onMouseRelease(int x, int y, int btn)
	{
		super.onMouseRelease(x,y,btn);
		ON = !ON;
	}
}
