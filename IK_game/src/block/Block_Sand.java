package block;

public class Block_Sand extends Block
{

	public Block_Sand(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);

		this.setBlockMovementSlow(0.25);
		this.setTextureName("sand");
	}
}
