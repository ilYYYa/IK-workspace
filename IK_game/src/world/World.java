package world;

import Resources.Saver;
import block.Block;
import entity.Entity;

public class World
{
	private Entity[] entities = new Entity[0];
	private Chunk[][] chunks = new Chunk[0][0];
	private String worldname = "";
	
	public World(String worldname)
	{
		this.worldname = worldname;
	}
	
	public Chunk[][] getChunks()
	{
		return chunks;
	}
	
	public int blockPosToChunkArrayPos(int pos)
	{
		pos = pos>>4;
		int d = (chunks.length - 1)/2;
		pos = pos + d;
		if(pos < 0) return -1;
		if(pos >= chunks.length) return -1;
		return pos;
	}
	
	public void extendsChunkArray(int l)
	{
		Chunk[][] buff = new Chunk[l][l];
		for(int ix = 0; ix < buff.length; ix++)
		{
			for(int iy = 0; iy < buff[ix].length; iy++)
			{
				buff[ix][iy] = new Chunk(ix - (l-1)/2, iy - (l-1)/2);
			}
		}
		for(int ix = 0; ix < chunks.length; ix++)
		{
			for(int iy = 0; iy < chunks[ix].length; iy++)
			{
				int x = chunks[ix][iy].chunkPosX;
				int y = chunks[ix][iy].chunkPosY;
				buff[(l-1)/2 + x][(l-1)/2 + y] = chunks[ix][iy];
			}
		}
		chunks = buff;
	}
	
	public void setBlockMeta(BlockPos pos, int meta)
	{
		this.setBlockMeta(pos.posX, pos.posY, pos.level, meta);
	}
	
	public void setBlockMeta(int x, int y, BlockPos.blockPosLevel lvl, int meta)
	{
		int cx = blockPosToChunkArrayPos(x);
		while(cx == -1)
		{
			extendsChunkArray(chunks.length + 2);
			cx = blockPosToChunkArrayPos(x);
		}
		int cy = blockPosToChunkArrayPos(y);
		if(cy == -1)
		{
			System.err.println("at World.class at setBlockMeta(int x, int y, BlockPos.blockPosLevel lvl, int meta)\n\tcy == -1");
			return;
		}
		chunks[cx][cy].setBlockMeta(x, y, lvl, meta);
	}
	
	public void setBlock(BlockPos pos, Block block)
	{
		this.setBlock(pos.posX, pos.posY, pos.level, block);
	}
	
	public void setBlock(int x, int y, BlockPos.blockPosLevel lvl, Block block)
	{
		int cx = blockPosToChunkArrayPos(x);
		while(cx == -1)
		{
			extendsChunkArray(chunks.length + 2);
			cx = blockPosToChunkArrayPos(x);
		}
		int cy = blockPosToChunkArrayPos(y);
		if(cy == -1)
		{
			System.err.println("at World.class at setBlock(int x, int y, BlockPos.blockPosLevel lvl, Block block)\n\tcy == -1");
			return;
		}
		chunks[cx][cy].setBlock(x, y, lvl, block);
	}
	
	public int getBlockMeta(int x, int y, BlockPos.blockPosLevel lvl)
	{
		int cx = blockPosToChunkArrayPos(x);
		if(cx == -1) return 0;
		int cy = blockPosToChunkArrayPos(y);
		if(cy == -1) return 0;
		
		return chunks[x][y].getBlockMeta(x, y, lvl);
	}
	
	public int getBlockMeta(BlockPos pos)
	{
		return  getBlockMeta(pos.posX, pos.posY, pos.level);
	}
	
	public Block getBlock(int x, int y, BlockPos.blockPosLevel lvl)
	{
		int cx = blockPosToChunkArrayPos(x);
		if(cx == -1) return null;
		int cy = blockPosToChunkArrayPos(y);
		if(cy == -1) return null;
		
		return chunks[x][y].getBlock(x, y, lvl);
	}
	
	public Block getBlock(BlockPos pos)
	{
		return getBlock(pos.posX, pos.posY, pos.level);
	}
	
	public Entity[] getEntitiesInWorld()
	{
		return entities;
	}
	
	public void spawnEntity(Entity e)
	{
		Entity[] buff = new Entity[entities.length + 1];
		for(int i = 0; i < entities.length; i++)
		{
			buff[i] = entities[i];
			if(e.uid == entities[i].uid) e.uid = getUniqueId();
		}
		buff[buff.length - 1] = e;
		entities = buff;
	}
	
	public void despawnEntity(Entity e)
	{
		boolean found = false;
		Entity[] buff = new Entity[entities.length - 1];
		for(int i = 0; i < buff.length; i++)
		{
			if(e == entities[i]) found = true;
			else if(!found)buff[i] = entities[i];
			else buff[i - 1] = entities[i];
		}
		entities = buff;
	}
	
	public int getUniqueId()
	{
		int ret = 0;
		while(getEntityByUID(ret) != null) ret = (int)(Math.random() * Integer.MAX_VALUE);
		return ret;
	}
	
	public Entity getEntityByUID(int uid)
	{
		for(int i = 0; i < entities.length; i++)
		{
			if(uid == entities[i].uid) return entities[i];
		}
		return null;
	}
	
	public void WorldTick()
	{
		updateEntities();
	}
	
	public void updateEntities()
	{
		for(int i = 0; i < entities.length; i++)
		{
			entities[i].onEntityUpdate();
		}
	}
	
	public Saver getWorldSaver()
	{
		Saver saver = new Saver("worlds/" + this.worldname + ".world");
		
		saver.addInt(chunks.length, "ChunkSize");
		for(int ix = 0; ix < chunks.length; ix++)
		{
			for(int iy = 0; iy < chunks[ix].length; iy++)
			{
				saver.addInt(ix, "ChunkAX" + ix * chunks.length + iy);
				saver.addInt(iy, "ChunkAY" + ix * chunks.length + iy);
				chunks[ix][iy].writeToSaver(saver, ix * chunks.length + iy);
			}
		}
		
		saver.addInt(entities.length, "EntitiesSize");
		for(int i = 0; i < entities.length; i++)
		{
			entities[i].writeToSaver(saver, i);
		}
		
		return saver;
	}
}










































