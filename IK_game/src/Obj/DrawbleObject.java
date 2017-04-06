package Obj;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class DrawbleObject
{
	public double posX = 0;
	public double posY = 0;
	public double localPosX = 0;
	public double localPosY = 0;
	public double width = 0;
	public double height = 0;
	
	public abstract void draw(Graphics g);
	
	public void onMouseClick(MouseEvent event){}
	public void onMousePress(MouseEvent event){}
	public void onMouseRelease(MouseEvent event){}

	public void onMouseMove(MouseEvent event){}
	
	public void onKeyPress(KeyEvent event){}
	public void onKeyRelease(KeyEvent event){}
}
