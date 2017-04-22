package block;

public class BlockRegister
{
	private static Block[] registeredBlocks = new Block[0];
	
	public static Block registerBlock(Block block)
	{
		block.BlockUnlocalizedName = block.BlockUnlocalizedName.toLowerCase();
		
		if(getBlockById(block.BlockId) != null) return null;
		if(getBlockByUnlocalizedName(block.BlockUnlocalizedName) != null) return null;
		
		Block[] buff = new Block[registeredBlocks.length + 1];
		for(int i = 0; i < registeredBlocks.length; i++) buff[i] = registeredBlocks[i];
		buff[buff.length - 1] = block;
		registeredBlocks = buff;
		
		return block;
	}
	
	public static Block getBlockById(int id)
	{
		for(int i = 0; i < registeredBlocks.length; i++)
		{
			if(registeredBlocks[i].BlockId == id) return registeredBlocks[i];
		}
		return null;
	}
	
	public static Block getBlockByUnlocalizedName(String name)
	{
		for(int i = 0; i < registeredBlocks.length; i++)
		{
			if(registeredBlocks[i].BlockUnlocalizedName.equals(name)) return registeredBlocks[i];
		}
		return null;
	}
}
