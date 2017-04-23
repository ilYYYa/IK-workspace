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
	World world = new World("testing world");
	public Panel_playground(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		worldRenderer = new Panel_worldrenderer(0, 0, 1, 1, this);
		console = new Panel_console(0,0,1,1,this, worldRenderer);
		
		worldRenderer.setWorld(world);

		for(int ix = -32; ix < 32; ix++)
		{
			for(int iy = -32; iy < 32; iy++)
			{
				world.setBlock(ix, iy, BlockPos.blockPosLevel.BACK, Blocks.GRASS);
			}
		}
		
		for(int ix = -32; ix < 32; ix++)
		{
			world.setBlock(ix, -1, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(ix, 0, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(ix, 1, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
		}
		
		for(int iy = -32; iy < 32; iy++)
		{
			world.setBlock(-1, iy, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(0, iy, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(1, iy, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
		}
		
		world.setBlock(-1, -1, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(0, -1, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(1, -1, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(-1, 0, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(0, 0, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(1, 0, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(-1, 1, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(0, 1, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		world.setBlock(1, 1, BlockPos.blockPosLevel.BACK, Blocks.STONE);
		
		this.addChild(worldRenderer);
	}
	
	@Override
	public void onKeyPress(KeyEvent e)
	{
		if(e.getKeyCode() == 192 && !this.existChild(console)) this.addChild(console);
		if(e.getKeyCode() == 27 && this.existChild(console)) this.removeChild(console);
		super.onKeyPress(e);
	}
}
