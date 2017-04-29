package block;

import world.BlockPos;
import world.World;

public class Block_Lava extends Block
{

	public Block_Lava(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		
		this.setTextureName("lava");
		
		this.setBlockMovementSlow(1.0);
	}

	@Override
	public String getTextureNameByMetaData(World world, BlockPos pos)
	{
		int meta = world.getBlockMeta(pos);
		
		if(world.worldTicks % 10 == 0) meta++;
		
		if(meta > 7) meta = 0;
		
		world.setBlockMeta(pos, meta);
		
		if(meta == 0) return "lava";
		else if(meta < 5) return "lava" + meta;
		else if(meta == 5) return "lava3";
		else if(meta == 6) return "lava2";
		else return "lava1";
	}
}
