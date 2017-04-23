package entity;

import world.World;

public class PlayingPlayerEntity extends PlayerEntity
{
	public int playerGamemode = 0;

	public PlayingPlayerEntity(World world, int uid)
	{
		super(world, uid);
	}
	
	public void setPlayerGamemode(int gm)
	{
		this.playerGamemode = gm;
	}
	public int getPlayerGamemode()
	{
		return this.playerGamemode;
	}
}
