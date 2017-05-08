package block;

import java.awt.Color;
import java.awt.Graphics;

import Resources.TextureLoader;
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
	public void drawAtScreen(Graphics g, int x, int y, int width, int height, World world, BlockPos pos)
	{
		//Air doesn't drawind at world!
	}
	
	public String toString()
	{
		return "Block Air";
	}
}
