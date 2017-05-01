package world;

import java.util.ArrayList;

import Panel.Panel_worldrenderer;
import Resources.Saver;
import block.Block;
import entity.Entity;
import entity.player.EntityController;
import entity.player.PlayerEntity;
import trigger.Trigger;
import trigger.Triggers;
import world.BlockPos.blockPosLevel;

public class World
{
	private Entity[] entities = new Entity[0];
	private Chunk[][] chunks = new Chunk[0][0];
	private String worldname = "";
	
	private Trigger[] triggers = new Trigger[0];
	
	private PlayerEntity player = null;
	public EntityController controller = new EntityController();
	
	public Panel_worldrenderer theWorldRenderer = null;
	public long worldTicks = 0;
	
	public int worldGamemode = 0;
	
	public World(String worldname)
	{
		this.worldname = worldname;
	}
	
	public EntityController getController()
	{
		return this.controller;
	}
	
	public void setControllableEntity(Entity e)
	{
		this.getController().setControllableEntity(e);
	}
	
	public Trigger[] getTriggers()
	{
		return triggers;
	}
	
	public void addTrigger(Trigger trigger)
	{
		Trigger[] buff = new Trigger[triggers.length + 1];
		for(int i = 0; i < triggers.length; i++) buff[i] = triggers[i];
		buff[buff.length - 1] = trigger;
		triggers = buff;
	}
	
	public void removeTrigger(Trigger trigger)
	{
		int i = 0;
		Trigger[] buff = new Trigger[triggers.length - 1];
		for(i = 0; i < triggers.length && triggers[i] != trigger; i++) buff[i] = triggers[i];
		for(i++; i < triggers.length; i++) buff[i - 1] = triggers[i];
		triggers = buff;
	}
	
	public void removeTriggersAt(int x, int y)
	{
		for(int i = 0; i < triggers.length; i++)
		{
			if(triggers[i].isCrossingPoint(x,y))
			{
				removeTrigger(triggers[i]);
				i = -1;
			}
		}
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
			if(chunks.length == 0) extendsChunkArray(1);
			else extendsChunkArray(chunks.length + 2);
			cx = blockPosToChunkArrayPos(x);
		}
		int cy = blockPosToChunkArrayPos(y);
		if(cy == -1)
		{
			if(chunks.length == 0) extendsChunkArray(1);
			else extendsChunkArray(chunks.length + 2);
			cy = blockPosToChunkArrayPos(y);
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
			if(chunks.length == 0) extendsChunkArray(1);
			else extendsChunkArray(chunks.length + 2);
			cx = blockPosToChunkArrayPos(x);
		}
		int cy = blockPosToChunkArrayPos(y);
		if(cy == -1)
		{
			if(chunks.length == 0) extendsChunkArray(1);
			else extendsChunkArray(chunks.length + 2);
			cy = blockPosToChunkArrayPos(y);
		}

		chunks[cx][cy].setBlock(x, y, lvl, block);
		
		BlockPos buff = new BlockPos(x,y,lvl);		
		block.onBlockPlaced(this, buff);
		if(this.getBlock(buff.north()) != null)this.getBlock(buff.north()).onNeighborBlocksChanged(this, buff.north());
		if(this.getBlock(buff.south()) != null)this.getBlock(buff.south()).onNeighborBlocksChanged(this, buff.south());
		if(this.getBlock(buff.west()) != null)this.getBlock(buff.west()).onNeighborBlocksChanged(this, buff.west());
		if(this.getBlock(buff.east()) != null)this.getBlock(buff.east()).onNeighborBlocksChanged(this, buff.east());
	}
	
	public int getBlockMeta(int x, int y, BlockPos.blockPosLevel lvl)
	{
		int cx = blockPosToChunkArrayPos(x);
		if(cx == -1) return 0;
		int cy = blockPosToChunkArrayPos(y);
		if(cy == -1) return 0;
		
		return chunks[cx][cy].getBlockMeta(x, y, lvl);
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

		return chunks[cx][cy].getBlock(x, y, lvl);
	}
	
	public Block getBlock(BlockPos pos)
	{
		return getBlock(pos.posX, pos.posY, pos.level);
	}
	
	public Entity[] getEntitiesInWorld()
	{
		return entities;
	}
	
	public Entity getEntityAt(double x, double y)
	{
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i].pointOnEntity(x, y))
			{
				return entities[i];
			}
		}
		return null;
	}
	
	public <T extends Entity> ArrayList<T> getEntitiesInWorldByClass(Class<? extends T> cl)
	{
		ArrayList<T> ret = new ArrayList<T>();
		
		for(int i = 0; i < entities.length; i++) if(entities[i].getClass() == cl) ret.add((T)entities[i]);
		
		return ret;
	}
	
	public <T extends Entity> ArrayList<T> getEntitiesInWorldByClassAtPosWithRadius(Class<? extends T> cl, double x, double y, double r)
	{
		ArrayList<T> e = this.getEntitiesInWorldByClass(cl);
		ArrayList<T> ret = new ArrayList<T>();
		for(int i = 0; i < e.size(); i++) if(e.get(i).getDistanceTo(x, y) <= r) ret.add((T)e.get(i));
		return ret;
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
		Entity[] buff = new Entity[entities.length - 1];
		int ind = this.getEntityIndex(e);
		for(int i = 0; i < ind; i++) buff[i] = entities[i];
		for(int i = ind+1; i < entities.length; i++) buff[i-1] = entities[i];
		entities = buff;
	}
	
	public int getEntityIndex(Entity e)
	{
		for(int i = 0; i < entities.length; i++)
		{
			if(e == entities[i]) return i;
		}
		return -1;
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
		updatePlayer();
		
		sortEntities();
		updateEntities();
		
		updateTriggers();
		
		worldTicks++;
	}
	
	public void updatePlayer()
	{
		if(player != null)
		{
			if(!player.isEntityAlive())
			{
				player.HP += player.HPRegeneration;
				if(player.HP > player.maxHP)
				{
					player.HP = player.maxHP;
					player.respawn();
				}
			}
		}
	}
	
	public void updateTriggers()
	{
		for(int i = 0; i < triggers.length; i++)
		{
			triggers[i].onTriggerUpdate(this);
		}
	}
	
	public void sortEntities()
	{
		if(entities.length == 0) return;
		
		Entity buff = entities[0];

		for(int i = 0; i < entities.length-1; i++)
		{
			if(entities[i].getY() > entities[i+1].getY())
			{
				buff = entities[i];
				entities[i] = entities[i+1];
				entities[i+1] = buff;
				
				i = -1;
			}
		}
	}
	
	public void updateEntities()
	{
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i].shouldDespawn())
			{
				this.despawnEntity(entities[i]);
				i=-1;
				break;
			}
			
			if(entities[i].isEntityAlive())
			{
				if(this.getController().getControllableEntity() == entities[i]) this.getController().onEntityUpdate();
				else entities[i].onEntityUpdate();
			}
			else
			{
				this.despawnEntity(entities[i]);
				i=-1;
				break;
			}
		}
	}
	
	public void setGamemode(int gm)
	{
		this.worldGamemode = gm;
	}
	
	public int getGamemode()
	{
		return this.worldGamemode;
	}
	
	public Saver getWorldSaver()
	{
		Saver saver = new Saver("worlds/" + this.worldname + ".world");
		
		saver.addString(this.worldname, "WorldName");

		saver.addInt(this.worldGamemode, "WorldGamemode");
		
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
		
		saver.addInt(triggers.length, "TriggersSize");
		for(int i = 0; i < triggers.length; i++)
		{
			saver.addString(triggers[i].unlocalizedTriggerName, "TriggerUnlocalizedName" + i);
			saver.addInt(triggers[i].posX, "TriggerPosX"+i);
			saver.addInt(triggers[i].posY, "TriggerPosY"+i);
			saver.addInt(triggers[i].width, "TriggerWidth"+i);
			saver.addInt(triggers[i].height, "TriggerHeight"+i);
		}
		
		saver.addInt(entities.length, "EntitiesSize");
		for(int i = 0; i < entities.length; i++)
		{
			entities[i].writeToSaver(saver, i);
		}
		
		return saver;
	}
	
	public void readWorldFromSaver(Saver saver)
	{
		try
		{
			this.setWorldName(saver.getString("WorldName"));
		}
		catch(Exception e)
		{
			System.err.println("World can't read world name from saver. Setted emptyworld name");
			this.setWorldName(saver.getString("emptyworld"));
		}
		
		try
		{
			this.worldGamemode = saver.getInt("WorldGamemode");
		}
		catch(Exception e)
		{
			System.err.println("World can't read world GAMEMODE from saver");
		}
		
		int len = 0;
		
		try
		{
			len = saver.getInt("ChunkSize");
			chunks = new Chunk[len][len];
			for(int ix = 0; ix < len; ix++)
			{
				for(int iy = 0; iy < len; iy++)
				{
					chunks[ix][iy] = new Chunk(saver.getInt("ChunkAX" + ix * chunks.length + iy), saver.getInt("ChunkAY" + ix * chunks.length + iy));
					chunks[ix][iy].readFromSaver(saver, ix * chunks.length + iy);
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("World can't read chunks from saver.");
			chunks = new Chunk[0][0];
		}

		try
		{
			len = saver.getInt("TriggersSize");
			triggers = new Trigger[len];
			for(int i = 0; i < len; i++)
			{
				triggers[i] = Triggers.create(
						saver.getString("TriggerUnlocalizedName" + i),
						saver.getInt("TriggerPosX"+i),
						saver.getInt("TriggerPosY"+i),
						saver.getInt("TriggerWidth"+i),
						saver.getInt("TriggerHeight"+i));
			}
		}
		catch(Exception e)
		{
			System.err.println("World can't read triggers from saver.");
			triggers = new Trigger[0];
		}

		try
		{
			len = saver.getInt("EntitiesSize");
			//entities = new Entity[len];
			for(int i = 0; i < len; i++)
			{
				//reading entities...
			}
		}
		catch(Exception e)
		{
			System.err.println("World can't read entities from saver.");
			entities = new Entity[0];
		}
	}
	
	public double getDistanceToPlayerFromEntity(Entity e)
	{
		if(this.player == null) return -1;
		else return e.getDistanceToEntity(this.player);
	}

	public PlayerEntity getPlayer()
	{
		return this.player;
	}
	
	public void setPlayer(PlayerEntity pl)
	{
		this.player = pl;
		this.spawnEntity(this.player);
		if(this.getController().getControllableEntity() == null || this.getGamemode() == 0) this.setControllableEntity(pl);
	}
	
	public void disbandPlayer()
	{
		this.despawnEntity(this.player);
		player = null;
	}

	public void setWorldName(String wn)
	{
		this.worldname = wn;
	}
	public String getWorldName()
	{
		return this.worldname;
	}

	public Block getBlock(double x, double y, blockPosLevel lvl) 
	{
		return this.getBlock(x < 0 ? (int)x - 1 : (int)x, y < 0 ? (int)y - 1 : (int)y, lvl);
	}

	public Entity[] getEntitiesByName(String name)
	{
		ArrayList<Entity> ret = new ArrayList<Entity>();
		
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i].unlocalizedName.toLowerCase().equals(name.toLowerCase())) ret.add(entities[i]);
		}
		
		return ret.toArray(new Entity[0]);
	}
}










































