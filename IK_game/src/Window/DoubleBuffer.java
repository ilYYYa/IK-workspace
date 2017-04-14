package Window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Obj.DrawbleObject;
import Scene.GlobalScene;
import Scene.Scene_Logo;

public class DoubleBuffer extends JPanel
{
	public GlobalScene Scene;
	public Listeners listeners;
	public MainWindow window;
	
	public DoubleBuffer(MainWindow w)
	{
		window = w;
		
		listeners = new Listeners(this);
		this.addMouseListener(listeners);
		this.addMouseMotionListener(listeners);
		
		Scene = new Scene_Logo();
	}
	
	public void setScene(GlobalScene scene)
	{
		Scene = scene;
	}
	
	public void logic()
	{
		if(Scene != null) logicObjectsIn(Scene);
	}
	
	private void logicObjectsIn(DrawbleObject obj)
	{
		obj.logic();
		for(int i = 0; i < obj.childs.size(); i++)
		{
			logicObjectsIn(obj.childs.get(i));
		}
	}
	
	private void drawObjectsIn(DrawbleObject obj, Graphics g)
	{
		obj.draw(g);
		for(int i = 0; i < obj.childs.size(); i++)
		{
			drawObjectsIn(obj.childs.get(i), g);
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		if(Scene != null) drawObjectsIn(Scene, g);
	}
	
	public void onMouseClick(MouseEvent event)
	{
		if(Scene != null) Scene.onMouseClick(event);
	}
	public void onMousePress(MouseEvent event)
	{
		if(Scene != null) Scene.onMousePress(event);
	}
	public void onMouseRelease(MouseEvent event)
	{
		if(Scene != null) Scene.onMouseRelease(event);
	}

	public void onMouseMove(MouseEvent event)
	{
		if(Scene != null) Scene.onMouseMove(event);
	}

	public void onKeyPress(KeyEvent event)
	{
		if(Scene != null) Scene.onKeyPress(event);
	}
	public void onKeyRelease(KeyEvent event)
	{
		if(Scene != null) Scene.onKeyRelease(event);
	}
}
