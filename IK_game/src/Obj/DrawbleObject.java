package Obj;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import Button.Button_back;
import Window.MainWindow;

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
		if(parent==null) return Game.Game.theGame.theMainWindow.width;
		else return width * parent.realWidth();
	}
	public double realHeight()
	{
		if(parent==null) return Game.Game.theGame.theMainWindow.height;
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
	public abstract void draw(MainWindow theMainWindow);
	
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
	
	public boolean focusOnMe()
	{
		if(parent == null) return true;
		if(parent.childs.length > 0 && parent.childs[parent.childs.length - 1] == this) return true;
		return false;
	}
	
	public void onMousePress(int x, int y, int btn)
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(checkingPointCrossingSomeObject(buff, x, y))
			{
				buff.onMousePress(x - (int)buff.realPosX(), y - (int)buff.realPosY(), btn);

				moveObject(i);
				
				break;
			}
			else
			{
				buff.onMousePressNotOnYou();
			}
		}
	}
	public void onMousePressNotOnYou()
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			buff.onMousePressNotOnYou();
		}
	}
	public void onMouseRelease(int x, int y, int btn)
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(checkingPointCrossingSomeObject(buff, x, y))
			{
				buff.onMouseRelease(x - (int)buff.realPosX(), y - (int)buff.realPosY(), btn);

				moveObject(i);
				
				break;
			}
			else
			{
				buff.onMouseReleaseNotOnYou();
			}
		}
	}
	public void onMouseReleaseNotOnYou()
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			buff.onMouseReleaseNotOnYou();
		}
	}

	public void onMouseMove(int x, int y, int dx, int dy)
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(checkingPointCrossingSomeObject(buff, x, y))
			{
				buff.onMouseMove(x - (int)buff.realPosX(), y - (int)buff.realPosY(), dx, dy);
			}
			else
			{
				buff.onMouseMoveNotOnYou(x - (int)buff.realPosX(), y - (int)buff.realPosY());
			}
		}
	}
	public void onMouseMoveNotOnYou(int x, int y)
	{
		for(int i = childs.length - 1; i >= 0; i--)
		{
			DrawbleObject buff = childs[i];
			
			if(!checkingPointCrossingSomeObject(buff, x, y))
			{
				buff.onMouseMoveNotOnYou(x - (int)buff.realPosX(), y - (int)buff.realPosY());
			}
		}
	}


	public void onMouseWheelMoved(int wheel)
	{
		if(childs.length > 0) childs[childs.length - 1].onMouseWheelMoved(wheel);
	}
	
	public void onKeyPress(int keyCode, String keyName)
	{
		if(childs.length > 0) childs[childs.length - 1].onKeyPress(keyCode, keyName);
	}
	public void onKeyRelease(int keyCode, String keyName)
	{
		if(childs.length > 0) childs[childs.length - 1].onKeyRelease(keyCode, keyName);
	}
}
