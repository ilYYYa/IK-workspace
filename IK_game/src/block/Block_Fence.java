package block;

public class Block_Fence extends Block
{
	public Block_Fence(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		
		this.setBlockMovementSlow(1.0D);
		this.setTextureName("fence");
	}
}
