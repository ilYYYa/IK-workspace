package Panel;

import java.awt.Color;

import Button.GButton_Switch;
import Obj.DrawbleObject;
import Window.MainWindow;

public class Panel_blocksTab_settings extends GlobalPanel
{
	public GButton_Switch ZBlockPos;
	
	public Panel_blocksTab_settings(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		ZBlockPos = new GButton_Switch(0.1, 0.025, 0.8, 0.95, this);
		ZBlockPos.setText("Z Pos");
		ZBlockPos.addTab("0");
		ZBlockPos.addTab("1");
		ZBlockPos.addTab("2");
		
		this.addChild(ZBlockPos);
	}

	@Override
	public void draw(MainWindow g)
	{
		g.setColor(Color.white);
		g.fillRect((int)this.posXOnScreen(), (int)this.posYOnScreen(), (int)this.realWidth(), (int)this.realHeight());
		g.setColor(Color.black);
		g.drawRect((int)this.posXOnScreen(), (int)this.posYOnScreen(), (int)this.realWidth(), (int)this.realHeight());
	}

}
