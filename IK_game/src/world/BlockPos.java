package world;

public class BlockPos
{
	public int posX = 0;
	public int posY = 0;
	public blockPosLevel level = blockPosLevel.BACK;
	
	public int metaData = 0;
	
	public BlockPos(int x, int y, blockPosLevel lvl)
	{
		posX = x; posY = y; level = lvl;
	}
	
	public void setMetaData(int metadata)
	{
		this.metaData = metadata;
	}
	
	public int getMetaData()
	{
		return this.metaData;
	}
	
	public BlockPos north()
	{
		return north(1);
	}
	public BlockPos north(int n)
	{
		return new BlockPos(posX, posY - n, level);
	}
	
	public BlockPos south()
	{
		return south(1);
	}
	public BlockPos south(int n)
	{
		return new BlockPos(posX, posY + n, level);		
	}
	
	public BlockPos east()
	{
		return east(1);
	}
	public BlockPos east(int n)
	{
		return new BlockPos(posX + n, posY, level);
	}
	
	public BlockPos west()
	{
		return west(1);
	}
	public BlockPos west(int n)
	{
		return new BlockPos(posX - n, posY, level);
	}
	
	public static enum blockPosLevel {BACK, MIDDLE, HIGH}

	public String toString()
	{
		return "BlockPos{x:" + this.posX + "; y:"+this.posY + "; Level: " + this.level +"; metadata:" + this.metaData + "}";
	}
}
