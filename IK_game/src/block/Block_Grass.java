package block;

import java.awt.Graphics;

import Resources.TextureLoader;
import world.BlockPos;
import world.World;

public class Block_Grass extends Block
{

	public Block_Grass(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);

		this.setTextureName("grass");
	}
	
	@Override
	public void setBlockMetaForThisBlock(World world, BlockPos pos)
	{
		int buff = 0;
		if(world.getBlock(pos.north())!=null && world.getBlock(pos.north()).isBlockSummaring() && world.getBlock(pos.north()) != this) buff += 1;
		if(world.getBlock(pos.south())!=null && world.getBlock(pos.south()).isBlockSummaring() && world.getBlock(pos.south()) != this) buff += 2;
		if(world.getBlock(pos.west())!=null && world.getBlock(pos.west()).isBlockSummaring() && world.getBlock(pos.west()) != this) buff += 4;
		if(world.getBlock(pos.east())!=null && world.getBlock(pos.east()).isBlockSummaring() && world.getBlock(pos.east()) != this) buff += 8;
		world.setBlockMeta(pos, buff);
	}

	@Override
	public String getTextureNameByMetaData(World world, BlockPos pos)
	{
		int meta = world.getBlockMeta(pos);
		if(meta == 0) return getTextureName();
		
		String buff = "";

		if((meta & 8) == 8) buff += "&" + world.getBlock(pos.east()).getTextureName() + "&8";
		if((meta & 4) == 4) buff += "&" + world.getBlock(pos.west()).getTextureName() + "&4";
		if((meta & 2) == 2) buff += "&" + world.getBlock(pos.south()).getTextureName() + "&2";
		if((meta & 1) == 1) buff += "&" + world.getBlock(pos.north()).getTextureName() + "&1";
		
		return "!001" + getTextureName() + buff;
	}
}
