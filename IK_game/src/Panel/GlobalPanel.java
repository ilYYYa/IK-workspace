package Panel;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Obj.DrawbleObject;

public class GlobalPanel extends DrawbleObject
{
	public ArrayList<DrawbleObject> objectsForDraw = new ArrayList<DrawbleObject>();
	
	public void addObjectForDraw(DrawbleObject obj)
	{
		objectsForDraw.add(obj);
	}
	
	public GlobalPanel(double x, double y, double w, double h)
	{
		posX = x;
		posY = y;
		width = w;
		height = h;
	}
	
	public void draw(Graphics g)
	{
		for(int i = 0; i < objectsForDraw.size(); i++)
		{
			objectsForDraw.get(i).posX = this.posX + objectsForDraw.get(i).localPosX;
			objectsForDraw.get(i).posY = this.posY + objectsForDraw.get(i).localPosY;
			objectsForDraw.get(i).draw(g);
		}
	}

	@Override
	public void onMouseClick(MouseEvent event)
	{
		for(int i = objectsForDraw.size() - 1; i >= 0; i--)
		{
			if(objectsForDraw.get(i).posX <= event.getX() && objectsForDraw.get(i).posX + objectsForDraw.get(i).width >= event.getX() &&
					objectsForDraw.get(i).posY <= event.getY() && objectsForDraw.get(i).posY + objectsForDraw.get(i).height >= event.getY())
			{
				objectsForDraw.get(i).onMouseClick(event);
				
				DrawbleObject obj = objectsForDraw.get(i);
				objectsForDraw.remove(i);
				objectsForDraw.add(obj);
				
				break;
			}
		}
	}
	@Override
	public void onMousePress(MouseEvent event)
	{
		for(int i = objectsForDraw.size() - 1; i >= 0; i--)
		{
			if(objectsForDraw.get(i).posX <= event.getX() && objectsForDraw.get(i).posX + objectsForDraw.get(i).width >= event.getX() &&
					objectsForDraw.get(i).posY <= event.getY() && objectsForDraw.get(i).posY + objectsForDraw.get(i).height >= event.getY())
			{
				objectsForDraw.get(i).onMousePress(event);
				
				DrawbleObject obj = objectsForDraw.get(i);
				objectsForDraw.remove(i);
				objectsForDraw.add(obj);
				
				break;
			}
		}
	}
	@Override
	public void onMouseRelease(MouseEvent event)
	{
		for(int i = objectsForDraw.size() - 1; i >= 0; i--)
		{
			if(objectsForDraw.get(i).posX <= event.getX() && objectsForDraw.get(i).posX + objectsForDraw.get(i).width >= event.getX() &&
					objectsForDraw.get(i).posY <= event.getY() && objectsForDraw.get(i).posY + objectsForDraw.get(i).height >= event.getY())
			{
				objectsForDraw.get(i).onMouseRelease(event);
				
				DrawbleObject obj = objectsForDraw.get(i);
				objectsForDraw.remove(i);
				objectsForDraw.add(obj);
				
				break;
			}
		}
	}
	
	@Override
	public void onMouseMove(MouseEvent event)
	{
		for(int i = objectsForDraw.size() - 1; i >= 0; i--)
		{
			if(objectsForDraw.get(i).posX <= event.getX() && objectsForDraw.get(i).posX + objectsForDraw.get(i).width >= event.getX() &&
					objectsForDraw.get(i).posY <= event.getY() && objectsForDraw.get(i).posY + objectsForDraw.get(i).height >= event.getY())
			{
				objectsForDraw.get(i).onMouseRelease(event);
				
				break;
			}
		}
	}

	@Override
	public void onKeyPress(KeyEvent event)
	{
		if(objectsForDraw.size() > 0) objectsForDraw.get(objectsForDraw.size() - 1).onKeyPress(event);
	}
	@Override
	public void onKeyRelease(KeyEvent event)
	{
		if(objectsForDraw.size() > 0) objectsForDraw.get(objectsForDraw.size() - 1).onKeyRelease(event);
	}
}
