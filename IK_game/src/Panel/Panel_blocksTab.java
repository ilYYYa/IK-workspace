package Panel;

import java.awt.Color;

import Button.GButton_Switch;
import Obj.DrawbleObject;
import Window.MainWindow;
import block.BlockRegister;

public class Panel_blocksTab extends GlobalPanel
{
	public Panel_blocksTab_settings setts;
	public Panel_blocksTab_blocks blocks;
	
	public Panel_blocksTab(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		setts = new Panel_blocksTab_settings(0, 0, 1, 0.05, this);
		blocks = new Panel_blocksTab_blocks(0, 0.05, 1, 0.95, this);
		
		this.addChild(setts);
		this.addChild(blocks);
	}
	
	@Override
	public boolean isAlwaysOnTop()
	{
		return true;
	}
}
