package block;

public class Blocks
{
	public static final Block AIR;
	
	static
	{
		AIR = BlockRegister.registerBlock(new Block_Air(0, "air"));
	}
}
