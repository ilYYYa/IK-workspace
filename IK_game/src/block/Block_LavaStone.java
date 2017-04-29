package block;

public class Block_LavaStone extends Block
{

	public Block_LavaStone(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		
		this.setTextureName("lavastone");
		this.setBlockMovementSlow(0.5);
	}

}
