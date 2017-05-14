package Scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import Obj.DrawbleObject;
import Panel.GlobalPanel;
import Window.MainWindow;

public class GlobalScene extends DrawbleObject
{
	public GlobalScene()
	{
		super(null);
	}
	
	public void addPanel(GlobalPanel panel)
	{
		if(!existsPanel(panel) && panel instanceof GlobalPanel) this.addChild(panel);
	}
	public void removePanel(GlobalPanel panel)
	{
		if(existsPanel(panel) && panel instanceof GlobalPanel) this.removeChild(panel);
	}
	public boolean existsPanel(GlobalPanel panel)
	{
		return this.existChild(panel);
	}

	public void logic(){}
	public void draw(MainWindow g){}
}
