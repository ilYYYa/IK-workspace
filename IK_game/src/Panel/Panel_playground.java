package Panel;

import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import Game.Game;
import Obj.DrawbleObject;
import block.Blocks;
import world.BlockPos;
import world.World;

public class Panel_playground extends GlobalPanel
{
	Panel_worldrenderer worldRenderer;
	Panel_console console;
	public Panel_blocksTab blocksTab;
	public Panel_playground(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		worldRenderer = new Panel_worldrenderer(0, 0, 1, 1, this);
		console = new Panel_console(0,0,1,1,this, worldRenderer);
		blocksTab = new Panel_blocksTab(0.85, 0, 0.15, 1, this);
		
		worldRenderer.setWorld(Game.theGame.currentOpenedWorld);
		
		this.addChild(worldRenderer);
	}
	
	@Override
	public void onKeyPress(int code, String name)
	{
		if(code == Keyboard.KEY_GRAVE && !this.existChild(console)) this.addChild(console);
		if(code == Keyboard.KEY_ESCAPE) if(this.existChild(console)) this.removeChild(console);
		
		if(code == Keyboard.KEY_F4 && !this.existChild(blocksTab) && worldRenderer.world.getGamemode() == 1)
		{
			this.addChild(blocksTab);
			worldRenderer.setWidth(0.85);
		}
		else if(code == Keyboard.KEY_F4 && this.existChild(blocksTab))
		{
			this.removeChild(blocksTab);
			worldRenderer.setWidth(1);
		}
		
		super.onKeyPress(code, name);
	}
}
