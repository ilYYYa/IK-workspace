package entity;

import world.World;

public class GenericalHumanEntity extends LivingEntity
{

	public GenericalHumanEntity(World world, String unlocalizedname, int id, int uid)
	{
		super(world, unlocalizedname, id, uid);
		this.setWidth(1D);
		this.setHeight(1.8D);
	}

}
