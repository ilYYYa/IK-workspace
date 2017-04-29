package block;

public class Blocks
{
	public static final Block AIR;
	public static final Block GRASS;
	public static final Block DIRT;
	public static final Block STONE;
	public static final Block LAVA;
	public static final Block LAVASTONE;
	
	static
	{
		AIR = BlockRegister.registerBlock(new Block_Air(0, "air"));
		GRASS = BlockRegister.registerBlock(new Block_Grass(1, "grass"));
		DIRT = BlockRegister.registerBlock(new Block_Dirt(2, "dirt"));
		STONE = BlockRegister.registerBlock(new Block_Stone(3, "stone"));
		LAVA = BlockRegister.registerBlock(new Block_Lava(4, "lava"));
		LAVASTONE = BlockRegister.registerBlock(new Block_LavaStone(5, "lavastone"));
	}
}
