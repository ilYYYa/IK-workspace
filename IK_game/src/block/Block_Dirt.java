package block;

import world.BlockPos;
import world.World;

public class Block_Dirt extends Block
{

	public Block_Dirt(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);

		this.setTextureName("dirt");
	}
}
