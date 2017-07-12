package block;

public class Blocks
{
	public static final Block AIR;
	public static final Block GRASS;
	public static final Block DIRT;
	public static final Block STONE;
	public static final Block LAVA;
	public static final Block LAVASTONE;
	public static final Block GRASSWITHFWHITELOWER;
	public static final Block GRASSWITHFREDLOWER;
	public static final Block GRASSWITHFYELLOWLOWER;
	public static final Block TREE;
	public static final Block UNICORN;
	public static final Block TECH_BLOCK_MOVESTOPPER;
	public static final Block BLUEHOUSE;
	public static final Block REDHOUSE1;
	public static final Block REDHOUSE2;
	public static final Block FENCE;
	public static final Block FOUNTAIN;
	public static final Block SAND;
	
	
	static
	{
		AIR = BlockRegister.registerBlock(new Block_Air(0, "air"));
		GRASS = BlockRegister.registerBlock(new Block_Grass(1, "grass"));
		DIRT = BlockRegister.registerBlock(new Block_Dirt(2, "dirt"));
		STONE = BlockRegister.registerBlock(new Block_Stone(3, "stone"));
		LAVA = BlockRegister.registerBlock(new Block_Lava(4, "lava"));
		LAVASTONE = BlockRegister.registerBlock(new Block_LavaStone(5, "lavastone"));
		GRASSWITHFWHITELOWER = BlockRegister.registerBlock(new Block_GrassWithWhiteFlower(6, "grasswithwhiteflower"));
		GRASSWITHFREDLOWER = BlockRegister.registerBlock(new Block_GrassWithRedFlower(7, "grasswithredflower"));
		GRASSWITHFYELLOWLOWER = BlockRegister.registerBlock(new Block_GrassWithYellowFlower(8, "grasswithyellowflower"));
		TREE = BlockRegister.registerBlock(new HugeBlock_Tree(9, "tree"));
		UNICORN = BlockRegister.registerBlock(new HugeBlock_Unicorn(10, "unicorn"));
		TECH_BLOCK_MOVESTOPPER = BlockRegister.registerBlock(new TECH_BLOCK_MOVESTOPPER(11, "tech_movestopper"));
		BLUEHOUSE = BlockRegister.registerBlock(new HugeBlock_BlueHouse(12, "bluehouse"));
		REDHOUSE1 = BlockRegister.registerBlock(new HugeBlock_RedHouse1(13, "redhouse"));
		REDHOUSE2 = BlockRegister.registerBlock(new HugeBlock_RedHouse2(14, "redhouse2"));
		FENCE = BlockRegister.registerBlock(new Block_Fence(15, "fence"));
		FOUNTAIN = BlockRegister.registerBlock(new HugeBlock_Fountain(16, "fountain"));
		SAND = BlockRegister.registerBlock(new Block_Sand(17, "sand"));
	}
}
