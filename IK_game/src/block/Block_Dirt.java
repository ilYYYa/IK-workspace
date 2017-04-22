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

	@Override
	public String getTextureNameByMetaData(World world, BlockPos pos)
	{
		int meta = world.getBlockMeta(pos);
		if(meta == 0) return getTextureName();
		
		String buff = "";

		if((meta & 8) == 8) buff = world.getBlock(pos.east()).getTextureName();
		if((meta & 4) == 4) buff = world.getBlock(pos.west()).getTextureName();
		if((meta & 2) == 2) buff = world.getBlock(pos.south()).getTextureName();
		if((meta & 1) == 1) buff = world.getBlock(pos.north()).getTextureName();
		
		return "!001" + getTextureName() + "&" + buff + "&" + meta;
	}
}
