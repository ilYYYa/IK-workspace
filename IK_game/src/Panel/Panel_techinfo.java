package Panel;

import java.awt.Color;
import java.awt.Graphics;

import Game.GameInfo;
import Obj.DrawbleObject;

public class Panel_techinfo extends GlobalPanel
{
	Panel_worldrenderer renderer;

	public Panel_techinfo(double x, double y, double w, double h, Panel_worldrenderer parent)
	{
		super(x, y, w, h, parent);
		renderer = parent;
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.drawString("" + GameInfo.getGameFullestName(), (int)this.realPosX() + 5, (int)this.realPosY() + 15*1);
		g.drawString("Mouse pos: " + renderer.mouseX + " : " + renderer.mouseY, (int)this.realPosX() + 5, (int)this.realPosY() + 15*2);
		g.drawString("Mouse block pos: " + renderer.mouseOnBlockX + " : " + renderer.mouseOnBlockY, (int)this.realPosX() + 5, (int)this.realPosY() + 15*3);
		g.drawString("Mouse block inted pos: " + renderer.getIntedMouseX() + " : " + renderer.getIntedMouseY(), (int)this.realPosX() + 5, (int)this.realPosY() + 15*4);
		g.drawString("Camera pos: " + renderer.cameraPosX + " : " + renderer.cameraPosY, (int)this.realPosX() + 5, (int)this.realPosY() + 15*5);
		g.drawString("Blocks per width: " + renderer.blocksAtScreenByWidth, (int)this.realPosX() + 5, (int)this.realPosY() + 15*6);
		g.drawString("Blocks per height: " + renderer.blocksAtScreenByHeight(), (int)this.realPosX() + 5, (int)this.realPosY() + 15*7);
	}

}
