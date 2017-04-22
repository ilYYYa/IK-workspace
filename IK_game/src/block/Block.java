package block;

import java.awt.Graphics;

import Resources.TextureLoader;
import entity.Entity;
import entity.PlayerEntity;
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
	
	public String getTextureNameByMetaData(int metadata)
	{
		return getTextureName();
	}
	
	public void onBlockClicked(World world, BlockPos pos, PlayerEntity player)
	{
		
	}
	
	public void onBlockPlaced(World world, BlockPos pos)
	{
		
	}
	
	public void onNeighborBlocksChanged(World world, BlockPos pos)
	{
		
	}
	
	public void onEntityWallkingOnBlock(World world, BlockPos pos, Entity entity)
	{
		
	}
	
	public void onBlockDestroyed(World world, BlockPos pos)
	{
		
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

	public void drawAtScreen(Graphics g, int x, int y, int width, int height, int metadata)
	{
		g.drawImage(TextureLoader.getTextureByName(this.getTextureNameByMetaData(metadata)), x, y, width, height, null);
	}
}



























