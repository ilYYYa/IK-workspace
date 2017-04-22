package block;

public class Block_Air extends Block
{
	public Block_Air(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		
		this.setBlockLightLevel(0);
		this.setBlockMovementSlow(0.0);
	}
}
