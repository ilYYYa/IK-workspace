package block;

import Window.MainWindow;
import world.BlockPos;
import world.World;

public class Block_Air extends Block
{
	public Block_Air(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);

		this.setTextureName("air");
	}
	
	@Override
	public void drawAtScreen(MainWindow g, int x, int y, int width, int height, World world, BlockPos pos)
	{
		//Air doesn't drawind at world!
	}
	
	public String toString()
	{
		return "Block Air";
	}
}
