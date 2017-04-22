package block;

import java.awt.Graphics;

import Resources.TextureLoader;

public class Block_Grass extends Block
{

	public Block_Grass(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);

		this.setTextureName("grass");
	}
}
