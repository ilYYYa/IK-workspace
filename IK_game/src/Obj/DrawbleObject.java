package Obj;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Button.Button_back;

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
	
	public DrawbleObject[] childs = new DrawbleObject[0];

	public DrawbleObject(DrawbleObject parent)
	{
		this.parent = parent;
	}
	
	public void moveObject(int index)
	{
		if(index >= childs.length) return;
		
		DrawbleObject[] buff = new DrawbleObject[childs.length];
		int i = 0;
		for(i = 0; i < childs.length && i != index; i++) buff[i] = childs[i];
		for(i++; i < childs.length; i++) buff[i-1] = childs[i];
		buff[buff.length - 1] = childs[index];
		childs = buff;
	}
	
	public void addChild(DrawbleObject ch)
	{
		if(existChild(ch)) return;
		
		DrawbleObject[] buff = new DrawbleObject[childs.length + 1];
		for(int i = 0; i < childs.length; i++) buff[i] = childs[i];
		buff[buff.length - 1] = ch;
		childs = buff;
	}
	
	public void removeChild(DrawbleObject ch)
	{
		if(!existChild(ch)) return;
		
		DrawbleObject[] buff = new DrawbleObject[childs.length - 1];
		int i = 0;
		for(i = 0; i < childs.length && childs[i] != ch; i++) buff[i] = childs[i];
		for(i++; i < childs.length; i++) buff[i-1] = childs[i];
		childs = buff;
	}
	
	public boolean existChild(DrawbleObject ch)
	{
		for(int i = 0; i < childs.length; i++) if(childs[i] == ch) return true;
		return false;
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
	
	public boolean checkingPointCrossingThisObject(double pointX, double pointY)
	{		
		if(this.realPosX() <= pointX && this.realPosX() + this.realWidth() >= pointX && this.realPosY() <= pointY && this.realPosY() + this.realHeight() >= pointY)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean checkingPointCrossingThisObjectWithScreenPos(double pointX, double pointY)
	{		
		if(this.posXOnScreen() <= pointX && this.posXOnScreen() + this.realWidth() >= pointX && this.posYOnScreen() <= pointY && this.posYOnScreen() + this.realHeight() >= pointY)
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
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMouseClick(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));

				moveObject(i);
				
				break;
			}
		}
	}
	public void onMousePress(MouseEvent event)
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMousePress(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));

				moveObject(i);
				
				break;
			}
		}
	}
	public void onMouseRelease(MouseEvent event)
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMouseRelease(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));

				moveObject(i);
				
				break;
			}
		}
	}

	public void onMouseMove(MouseEvent event)
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
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
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(!checkingPointCrossingSomeObject(buff, event.getX(), event.getY()))
			{
				buff.onMouseMoveNotOnYou(getNewMouseEvent(event, event.getX() - (int)buff.realPosX(), event.getY() - (int)buff.realPosY()));
			}
		}
	}
	
	public void onKeyPress(KeyEvent event)
	{
		if(childs.length > 0) childs[childs.length - 1].onKeyPress(event);
	}
	public void onKeyRelease(KeyEvent event)
	{
		if(childs.length > 0) childs[childs.length - 1].onKeyRelease(event);
	}
}
