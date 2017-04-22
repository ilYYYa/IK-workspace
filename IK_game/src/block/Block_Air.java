package block;

import java.awt.Color;
import java.awt.Graphics;

public class Block_Air extends Block
{
	public Block_Air(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);

		this.setTextureName("air");
	}
	
	public String toString()
	{
		return "Block Air";
	}
}
