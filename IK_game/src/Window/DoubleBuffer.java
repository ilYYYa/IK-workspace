package Window;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Scene.GlobalScene;

public class DoubleBuffer extends JPanel
{
	public static GlobalScene Scene = null;
	public static Listeners listeners;
	
	public DoubleBuffer()
	{
		listeners = new Listeners(this);
		this.addMouseListener(listeners);
		this.addKeyListener(listeners);
	}
	
	public static void setScene(GlobalScene scene)
	{
		Scene = scene;
	}
	
	@Override
	public void paint(Graphics g)
	{
		if(Scene != null) Scene.draw(g);
	}
	
	public static void onMouseClick(MouseEvent event)
	{
		if(Scene != null) Scene.onMouseClick(event);
	}
	public static void onMousePress(MouseEvent event)
	{
		if(Scene != null) Scene.onMousePress(event);
	}
	public static void onMouseRelease(MouseEvent event)
	{
		if(Scene != null) Scene.onMouseRelease(event);
	}

	public static void onMouseMove(MouseEvent event)
	{
		if(Scene != null) Scene.onMouseMove(event);
	}

	public static void onKeyPress(KeyEvent event)
	{
		if(Scene != null) Scene.onKeyPress(event);
	}
	public static void onKeyRelease(KeyEvent event)
	{
		if(Scene != null) Scene.onKeyRelease(event);
	}
}
