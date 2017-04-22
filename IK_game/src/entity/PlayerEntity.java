package entity;

import world.World;

public class PlayerEntity extends LivingEntity
{

	public PlayerEntity(World world, int uid)
	{
		super(world, "player", 0, uid);
	}
	
}
