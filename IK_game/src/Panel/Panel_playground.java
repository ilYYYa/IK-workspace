package Panel;

import java.awt.event.KeyEvent;

import Game.Game;
import Obj.DrawbleObject;
import block.Blocks;
import world.BlockPos;
import world.World;

public class Panel_playground extends GlobalPanel
{
	Panel_worldrenderer worldRenderer;
	Panel_console console;
	public Panel_playground(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		worldRenderer = new Panel_worldrenderer(0, 0, 1, 1, this);
		console = new Panel_console(0,0,1,1,this, worldRenderer);
		
		worldRenderer.setWorld(Game.theGame.currentOpenedWorld);
		
		this.addChild(worldRenderer);
	}
	
	@Override
	public void onKeyPress(KeyEvent e)
	{
		if(e.getKeyCode() == 192 && !this.existChild(console)) this.addChild(console);
		if(e.getKeyCode() == 27)
		{
			if(this.existChild(console)) this.removeChild(console);
		}
		
		super.onKeyPress(e);
	}
}
