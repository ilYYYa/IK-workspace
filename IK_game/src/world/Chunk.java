package world;

import Resources.Saver;
import block.Block;

public class Chunk
{
	public int chunkPosX = 0;
	public int chunkPosY = 0;
	
	private int[][] backBlocksId = new int[16][16];
	private int[][] middleBlocksId = new int[16][16];
	private int[][] highBlocksId = new int[16][16];

	private int[][] backBlocksMeta = new int[16][16];
	private int[][] middleBlocksMeta = new int[16][16];
	private int[][] highBlocksMeta = new int[16][16];
	
	public Chunk(int chunkX, int chunkY)
	{
		chunkPosX = chunkX;
		chunkPosY = chunkY;
	}
	
	public void setBlockMeta(BlockPos pos, int meta)
	{
		setBlockMeta(pos.posX, pos.posY, pos.level, meta);
	}
	public void setBlockMeta(int x, int y, BlockPos.blockPosLevel lvl, int meta)
	{
		x = x - 16 * (x >> 4);
		y = y - 16 * (y >> 4);
		
		if(lvl == BlockPos.blockPosLevel.BACK) backBlocksMeta[x][y] = meta;
		if(lvl == BlockPos.blockPosLevel.MIDDLE) middleBlocksMeta[x][y] = meta;
		if(lvl == BlockPos.blockPosLevel.HIGH) highBlocksMeta[x][y] = meta;
	}
	public int getBlockMeta(BlockPos pos)
	{
		return getBlockMeta(pos.posX, pos.posY, pos.level);
	}
	public int getBlockMeta(int x, int y, BlockPos.blockPosLevel lvl)
	{
		x = x - 16 * (x >> 4);
		y = y - 16 * (y >> 4);
		
		if(lvl == BlockPos.blockPosLevel.BACK) return backBlocksMeta[x][y];
		else if(lvl == BlockPos.blockPosLevel.MIDDLE) return middleBlocksMeta[x][y];
		else return highBlocksMeta[x][y];
	}

	public void setBlock(BlockPos pos, Block block)
	{
		setBlock(pos.posX, pos.posY, pos.level, block.BlockId);
	}
	public void setBlock(int x, int y, BlockPos.blockPosLevel lvl, Block block)
	{
		setBlock(x,y,lvl,block.BlockId);
	}
	public void setBlock(int x, int y, BlockPos.blockPosLevel lvl, int id)
	{
		x = x - 16 * (x >> 4);
		y = y - 16 * (y >> 4);
		
		if(lvl == BlockPos.blockPosLevel.BACK) backBlocksId[x][y] = id;
		if(lvl == BlockPos.blockPosLevel.MIDDLE) middleBlocksId[x][y] = id;
		if(lvl == BlockPos.blockPosLevel.HIGH) highBlocksId[x][y] = id;
	}
	
	public int getBlockId(int x, int y, BlockPos.blockPosLevel lvl)
	{
		x = x - 16 * (x >> 4);
		y = y - 16 * (y >> 4);
		
		if(lvl == BlockPos.blockPosLevel.BACK) return backBlocksId[x][y];
		else if(lvl == BlockPos.blockPosLevel.MIDDLE) return middleBlocksId[x][y];
		else return highBlocksId[x][y];
	}
	
	public Block getBlock(BlockPos pos)
	{
		return getBlock(pos.posX, pos.posY, pos.level);
	}
	public Block getBlock(int x, int y, BlockPos.blockPosLevel lvl)
	{
		return Block.getBlockByBlockId(getBlockId(x, y, lvl));
	}
	
	public void writeToSaver(Saver saver, int ci)
	{
		saver.addInt(chunkPosX, ci + "ChunkPosX");
		saver.addInt(chunkPosY, ci + "ChunkPosY");
		for(int i = 0; i < backBlocksId.length; i++) saver.addIntsArray(backBlocksId[i], ci + "backBlocksId" + i);
		for(int i = 0; i < middleBlocksId.length; i++) saver.addIntsArray(middleBlocksId[i], ci + "middleBlocksId" + i);
		for(int i = 0; i < highBlocksId.length; i++) saver.addIntsArray(highBlocksId[i], ci + "highBlocksId" + i);
		for(int i = 0; i < backBlocksMeta.length; i++) saver.addIntsArray(backBlocksMeta[i], ci + "backBlocksMeta" + i);
		for(int i = 0; i < middleBlocksMeta.length; i++) saver.addIntsArray(middleBlocksMeta[i], ci + "middleBlocksMeta" + i);
		for(int i = 0; i < highBlocksMeta.length; i++) saver.addIntsArray(highBlocksMeta[i], ci + "highBlocksMeta" + i);
	}
}





























