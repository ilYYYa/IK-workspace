package Scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Obj.DrawbleObject;
import Panel.GlobalPanel;

public class GlobalScene extends DrawbleObject
{
	public ArrayList<GlobalPanel> panels = new ArrayList<GlobalPanel>();
	
	public GlobalScene(double w, double h)
	{
		super(null, null);
		width = w; height = h;
	}
	
	public void addPanel(GlobalPanel panel)
	{
		if(!existsPanel(panel)) panels.add(panel);
	}
	public void removePanel(GlobalPanel panel)
	{
		if(existsPanel(panel)) panels.remove(panel);
	}
	public boolean existsPanel(GlobalPanel panel)
	{
		return panels.contains(panel);
	}
	
	@Override
	public void draw(Graphics g)
	{
		for(int i = 0; i < panels.size(); i++)
		{
			panels.get(i).draw(g);
		}
	}
	
	@Override
	public void onMouseClick(MouseEvent event)
	{
		for(int i = panels.size() - 1; i >= 0; i--)
		{
			if(CheckingPointCrossingSomeObject(panels.get(i), event.getX(), event.getY()))
			{
				panels.get(i).onMouseClick(event);

				GlobalPanel panelBuff = panels.get(i);
				panels.remove(i);
				panels.add(panelBuff);
				
				break;
			}
		}
	}
	@Override
	public void onMousePress(MouseEvent event)
	{
		for(int i = panels.size() - 1; i >= 0; i--)
		{
			if(CheckingPointCrossingSomeObject(panels.get(i), event.getX(), event.getY()))
			{
				panels.get(i).onMousePress(event);
				
				GlobalPanel panelBuff = panels.get(i);
				panels.remove(i);
				panels.add(panelBuff);
				
				break;
			}
		}
	}
	@Override
	public void onMouseRelease(MouseEvent event)
	{
		for(int i = panels.size() - 1; i >= 0; i--)
		{
			if(CheckingPointCrossingSomeObject(panels.get(i), event.getX(), event.getY()))
			{
				panels.get(i).onMouseRelease(event);

				GlobalPanel panelBuff = panels.get(i);
				panels.remove(i);
				panels.add(panelBuff);
				
				break;
			}
		}
	}
	
	@Override
	public void onMouseMove(MouseEvent event)
	{
		for(int i = panels.size() - 1; i >= 0; i--)
		{
			if(CheckingPointCrossingSomeObject(panels.get(i), event.getX(), event.getY()))
			{			
				panels.get(i).onMouseMove(event);
			}
			else
			{
				panels.get(i).onMouseMoveNotOnYou(event);
			}
		}
	}

	@Override
	public void onKeyPress(KeyEvent event)
	{
		if(panels.size() > 0) panels.get(panels.size() - 1).onKeyPress(event);
	}
	@Override
	public void onKeyRelease(KeyEvent event)
	{
		if(panels.size() > 0) panels.get(panels.size() - 1).onKeyRelease(event);
	}
}
