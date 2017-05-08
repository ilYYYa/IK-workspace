package block;

import java.awt.Graphics;

import Resources.TextureLoader;
import entity.Entity;
import entity.player.PlayerEntity;
import world.BlockPos;
import world.World;

public class Block
{
	public int BlockId = 0;
	public String BlockUnlocalizedName = "block";
	
	private String textureName = "";
	private String soundEntityWallkingOnBlockName = "";
	private double blockSlowMotion = 0.0;
	
	private int lighting = 0;
	
	private boolean summaring = true;

	public Block(int blockId, String blockUnlocalizedName)
	{
		this.BlockId = blockId;
		this.BlockUnlocalizedName = blockUnlocalizedName;
	}
	
	public static Block getBlockByBlockId(int id)
	{
		return BlockRegister.getBlockById(id);
	}

	public static Block getBlockByBlockUnlocalizedName(String name)
	{
		return BlockRegister.getBlockByUnlocalizedName(name);
	}
	
	public static int getIdFromBlock(Block block)
	{
		return block.BlockId;
	}
	
	public static String getUnlocalizedNameFromBlock(Block block)
	{
		return block.BlockUnlocalizedName;
	}
	
	public boolean isBlockSummaring()
	{
		return summaring;
	}
	
	public void setBlockSummaring(boolean value)
	{
		summaring = value;
	}
	
	public Block setSoundEntityWallkingOnBlockName(String name)
	{
		soundEntityWallkingOnBlockName = name;
		return this;
	}
	
	public String getSoundEntityWallkingOnBlockName()
	{
		return soundEntityWallkingOnBlockName;
	}
	
	public Block setTextureName(String texture)
	{
		textureName = texture;
		return this;
	}
	
	public String getTextureName()
	{
		return textureName;
	}
	
	public String getTextureNameByMetaData(World world, BlockPos pos)
	{
		return getTextureName();
	}
	
	public void onBlockClicked(World world, BlockPos pos, PlayerEntity player)
	{
		if(this.isBlockSummaring()) this.setBlockMetaForThisBlock(world, pos);
	}
	
	public void onBlockPlaced(World world, BlockPos pos)
	{
		if(this.isBlockSummaring()) this.setBlockMetaForThisBlock(world, pos);
	}
	
	public void onNeighborBlocksChanged(World world, BlockPos pos)
	{
		if(this.isBlockSummaring()) this.setBlockMetaForThisBlock(world, pos);
	}
	
	public void onEntityWallkingOnBlock(World world, BlockPos pos, Entity entity)
	{
		if(this.isBlockSummaring()) this.setBlockMetaForThisBlock(world, pos);
	}
	
	public void onBlockDestroyed(World world, BlockPos pos)
	{
		world.setBlock(pos, Blocks.AIR);
	}
	
	public void setBlockMetaForThisBlock(World world, BlockPos pos)
	{
		int buff = 0;
		if(world.getBlock(pos.north())!=null && world.getBlock(pos.north()).isBlockSummaring() && world.getBlock(pos.north()) != this) buff += 1;
		if(world.getBlock(pos.south())!=null && world.getBlock(pos.south()).isBlockSummaring() && world.getBlock(pos.south()) != this) buff += 2;
		if(world.getBlock(pos.west())!=null && world.getBlock(pos.west()).isBlockSummaring() && world.getBlock(pos.west()) != this) buff += 4;
		if(world.getBlock(pos.east())!=null && world.getBlock(pos.east()).isBlockSummaring() && world.getBlock(pos.east()) != this) buff += 8;
		world.setBlockMeta(pos, buff);
	}

	/** 0.0 - Passable block, 1.0 - not*/
	public Block setBlockMovementSlow(double slow)
	{
		this.blockSlowMotion = slow;
		return this;
	}
	
	/** 0.0 - Passable block, 1.0 - not*/
	public double getBlockMovementSlow()
	{
		return blockSlowMotion;
	}
	
	public boolean isPassable()
	{
		return blockSlowMotion < 1.0;
	}
	
	/** 0 - block without lighting; 15 and more - max block lighting */
	public Block setBlockLightLevel(int light)
	{
		this.lighting = light;
		return this;
	}

	/** 0 - block without lighting; 15 and more - max block lighting */
	public int getBlockLighting()
	{
		return this.lighting;
	}

	public void drawAtScreen(Graphics g, int x, int y, int width, int height, World world, BlockPos pos)
	{
		g.drawImage(TextureLoader.getTextureByName(this.getTextureNameByMetaData(world, pos)), x, y, width, height, null);
	}
	
	public String toString()
	{
		return this.BlockUnlocalizedName + ":Block";
	}
}



























