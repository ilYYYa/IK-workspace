package block;

import Resources.Texture;
import Resources.TextureLoader;
import Window.MainWindow;
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

	public void drawAtScreen(MainWindow g, int x, int y, int width, int height, World world, BlockPos pos)
	{
		String textureName = this.getTextureNameByMetaData(world, pos);
		Texture texture = TextureLoader.getTextureByName(textureName);
		g.drawTexture(texture, x, y, width, height);
	}
	
	public String toString()
	{
		return this.BlockUnlocalizedName + ":Block@" + this.BlockId;
	}
}



























