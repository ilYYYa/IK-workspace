package Obj;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class DrawbleObject
{
	public double posX = 0.0;
	public double posY = 0.0;

	public double width = 0.0;
	public double height = 0.0;
	
	public double realPosX()
	{
		if(parent==null) return 0;
		else return posX * parent.realWidth();
	}
	public double realPosY()
	{
		if(parent==null) return 0;
		else return posY * parent.realHeight();
	}
	
	public double posXOnScreen()
	{
		if(parent == null) return 0;
		return parent.posXOnScreen() + realPosX();
	}
	
	public double posYOnScreen()
	{
		if(parent == null) return 0;
		return parent.posYOnScreen() + realPosY();
	}
	
	public double realWidth()
	{
		if(parent==null) return Game.Game.theGame.theDoubleBuffer.getWidth();
		else return width * parent.realWidth();
	}
	public double realHeight()
	{
		if(parent==null) return Game.Game.theGame.theDoubleBuffer.getHeight();
		else return height * parent.realHeight();
	}
	
	public DrawbleObject parent = null;
	
	public ArrayList<DrawbleObject> childs = new ArrayList<DrawbleObject>();

	public DrawbleObject(DrawbleObject parent)
	{
		this.parent = parent;
	}
	
	public void addChild(DrawbleObject ch)
	{
		if(!childs.contains(ch)) 
		{
			childs.add(ch);
			ch.parent = this;
		}
	}
	
	public void removeChild(DrawbleObject ch)
	{
		if(childs.contains(ch))
		{
			childs.remove(ch);
			ch.parent = null;
		}
	}
	
	public boolean existChild(DrawbleObject ch)
	{
		return childs.contains(ch);
	}
	
	@Override
	public String toString()
	{
		return super.toString() +
				"\nlocal posistion: " + posX + ":" + posY +
				"\nlocal width and height: " + width + ":" + height +
				"\nreal position: " + realPosX() + ":" + realPosY() +
				"\nreal width and height: " + realWidth() + ":" + realHeight()+
				"\nscreen position: " + posXOnScreen() + ":" + posYOnScreen();
	}

	public abstract void logic();
	public abstract void draw(Graphics g);
	
	public boolean checkingPointCrossingSomeObject(DrawbleObject obj, double pointX, double pointY)
	{
		if(obj.realPosX() <= pointX && obj.realPosX() + obj.realWidth() >= pointX && obj.realPosY() <= pointY && obj.realPosY() + obj.realHeight() >= pointY)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	private MouseEvent getNewMouseEvent(MouseEvent e, int x, int y)
	{
		return new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), x, y, e.getClickCount(), e.isPopupTrigger(), e.getButton());
	}
	
	public void onMouseClick(MouseEvent event)
	{
		for(int i = childs.size() - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs.get(i);
			
			if(checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMouseClick(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));

				removeChild(buff);
				addChild(buff);
				
				break;
			}
		}
	}
	public void onMousePress(MouseEvent event)
	{
		for(int i = childs.size() - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs.get(i);
			
			if(checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMousePress(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));

				removeChild(buff);
				addChild(buff);
				
				break;
			}
		}
	}
	public void onMouseRelease(MouseEvent event)
	{
		for(int i = childs.size() - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs.get(i);
			
			if(checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMouseRelease(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));

				removeChild(buff);
				addChild(buff);
				
				break;
			}
		}
	}

	public void onMouseMove(MouseEvent event)
	{
		for(int i = childs.size() - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs.get(i);
			
			if(checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMouseMove(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));
			}
			else
			{
				buff.onMouseMoveNotOnYou(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));
			}
		}
	}
	public void onMouseMoveNotOnYou(MouseEvent event)
	{
		for(int i = childs.size() - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs.get(i);
			
			if(!checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMouseMoveNotOnYou(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));
			}
		}
	}
	
	public void onKeyPress(KeyEvent event)
	{
		if(childs.size() > 0) childs.get(childs.size() - 1).onKeyPress(event);
	}
	public void onKeyRelease(KeyEvent event)
	{
		if(childs.size() > 0) childs.get(childs.size() - 1).onKeyRelease(event);
	}
}
