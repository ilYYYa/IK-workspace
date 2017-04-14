package Panel;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Obj.DrawbleObject;
import Scene.GlobalScene;

public class GlobalPanel extends DrawbleObject
{
	public GlobalPanel(double x, double y, double w, double h, GlobalScene scene_parent)
	{
		super(scene_parent);
		posX = x;
		posY = y;
		width = w;
		height = h;
	}

	public void logic(){}
	public void draw(Graphics g){}
}
