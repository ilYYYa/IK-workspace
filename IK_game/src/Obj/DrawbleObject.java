package Obj;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Panel.GlobalPanel;
import Scene.GlobalScene;

public abstract class DrawbleObject
{
	public double posX = 0;
	public double posY = 0;
	public double localPosX = 0;
	public double localPosY = 0;
	public double width = 0;
	public double height = 0;
	
	public GlobalScene scene_parent = null;
	public GlobalPanel panel_parent = null;
	
	public DrawbleObject(GlobalScene sp, GlobalPanel pp)
	{
		scene_parent = sp; panel_parent = pp;
	}
	
	public abstract void draw(Graphics g);
	
	public boolean CheckingPointCrossingSomeObject(DrawbleObject obj, double pointX, double pointY)
	{
		if(obj.posX <= pointX && obj.posX + obj.width >= pointX && obj.posY <= pointY && obj.posY + obj.height >= pointY)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void onMouseClick(MouseEvent event){}
	public void onMousePress(MouseEvent event){}
	public void onMouseRelease(MouseEvent event){}

	public void onMouseMove(MouseEvent event){}
	public void onMouseMoveNotOnYou(MouseEvent event){}
	
	public void onKeyPress(KeyEvent event){}
	public void onKeyRelease(KeyEvent event){}
}
