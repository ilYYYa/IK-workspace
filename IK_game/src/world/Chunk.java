package world;

import Resources.Saver;
import block.Block;

public class Chunk
{
	public int chunkPosX = 0;
	public int chunkPosY = 0;
	
	private int[] backBlocksId = new int[256];
	private int[] middleBlocksId = new int[256];
	private int[] highBlocksId = new int[256];

	private int[] backBlocksMeta = new int[256];
	private int[] middleBlocksMeta = new int[256];
	private int[] highBlocksMeta = new int[256];
	
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
		
		int pos = x*16 + y;
		
		if(lvl == BlockPos.blockPosLevel.BACK) backBlocksMeta[pos] = meta;
		if(lvl == BlockPos.blockPosLevel.MIDDLE) middleBlocksMeta[pos] = meta;
		if(lvl == BlockPos.blockPosLevel.HIGH) highBlocksMeta[pos] = meta;
	}
	public int getBlockMeta(BlockPos pos)
	{
		return getBlockMeta(pos.posX, pos.posY, pos.level);
	}
	public int getBlockMeta(int x, int y, BlockPos.blockPosLevel lvl)
	{
		x = x - 16 * (x >> 4);
		y = y - 16 * (y >> 4);
		
		int pos = x*16 + y;
		
		if(lvl == BlockPos.blockPosLevel.BACK) return backBlocksMeta[pos];
		else if(lvl == BlockPos.blockPosLevel.MIDDLE) return middleBlocksMeta[pos];
		else return highBlocksMeta[pos];
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
		
		int pos = x*16 + y;
		
		if(lvl == BlockPos.blockPosLevel.BACK) backBlocksId[pos] = id;
		if(lvl == BlockPos.blockPosLevel.MIDDLE) middleBlocksId[pos] = id;
		if(lvl == BlockPos.blockPosLevel.HIGH) highBlocksId[pos] = id;
	}
	
	public int getBlockId(int x, int y, BlockPos.blockPosLevel lvl)
	{
		x = x - 16 * (x >> 4);
		y = y - 16 * (y >> 4);
		
		int pos = x*16 + y;
		
		if(lvl == BlockPos.blockPosLevel.BACK) return backBlocksId[pos];
		else if(lvl == BlockPos.blockPosLevel.MIDDLE) return middleBlocksId[pos];
		else return highBlocksId[pos];
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
		saver.addIntsArray(backBlocksId, ci + "backBlocksId");
		saver.addIntsArray(middleBlocksId, ci + "middleBlocksId");
		saver.addIntsArray(highBlocksId, ci + "highBlocksId");
		saver.addIntsArray(backBlocksMeta, ci + "backBlocksMeta");
		saver.addIntsArray(middleBlocksMeta, ci + "middleBlocksMeta");
		saver.addIntsArray(highBlocksMeta, ci + "highBlocksMeta");
	}
	
	public void readFromSaver(Saver saver, int ci)
	{
		chunkPosX = saver.getInt(ci + "ChunkPosX");
		chunkPosY = saver.getInt(ci + "ChunkPosY");
		backBlocksId = saver.IntegerArrayToIntArray(saver.getIntsArray(ci + "backBlocksId"));
		middleBlocksId = saver.IntegerArrayToIntArray(saver.getIntsArray(ci + "middleBlocksId"));
		highBlocksId = saver.IntegerArrayToIntArray(saver.getIntsArray(ci + "highBlocksId"));
		backBlocksMeta = saver.IntegerArrayToIntArray(saver.getIntsArray(ci + "backBlocksMeta"));
		middleBlocksMeta = saver.IntegerArrayToIntArray(saver.getIntsArray(ci + "middleBlocksMeta"));
		highBlocksMeta = saver.IntegerArrayToIntArray(saver.getIntsArray(ci + "highBlocksMeta"));
	}
}





























