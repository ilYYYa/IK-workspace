package Panel;

import Obj.DrawbleObject;
import block.Blocks;
import world.BlockPos;
import world.World;

public class Panel_playground extends GlobalPanel
{
	Panel_worldrenderer worldRenderer;
	World world = new World("testing world");
	public Panel_playground(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		worldRenderer = new Panel_worldrenderer(0, 0, 1, 1, this);
		worldRenderer.setWorld(world);

		for(int ix = -48; ix < 48; ix++)
		{
			for(int iy = -48; iy < 48; iy++)
			{
				world.setBlock(ix, iy, BlockPos.blockPosLevel.BACK, Blocks.GRASS);
			}
		}
		
		for(int ix = -48; ix < 48; ix++)
		{
			world.setBlock(ix, -1, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(ix, 0, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(ix, 1, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
		}
		
		for(int iy = -48; iy < 48; iy++)
		{
			world.setBlock(-1, iy, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(0, iy, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
			world.setBlock(1, iy, BlockPos.blockPosLevel.BACK, Blocks.DIRT);
		}
		
		this.addChild(worldRenderer);
	}
}
