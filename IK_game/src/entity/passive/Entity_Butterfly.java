package entity.passive;

import entity.LivingEntity;
import world.World;

public class Entity_Butterfly extends LivingEntity
{

	public Entity_Butterfly(World world, int uid)
	{
		super(world, "Butterfly", 2, uid);
		
		this.setWidth(0.5D);
		this.setHeight(0.5D);
		this.setHitboxWidth(0.5D);
		this.setHitboxHeight(0.5D);
		
		this.setMaxHP(1D);
		this.setMaxMP(0D);
		
		this.setGhostly(true);
		
		this.motion = 0.75 + Math.random();
	}
	
	@Override
	public void onEntityUpdate()
	{
		this.motionX = Math.random()*4 - 2;
		this.motionY = Math.random()*4 - 2;
		super.onEntityUpdate();
	}


	@Override
	public String getTexture()
	{
		if(this.lifeTime % 10 < 5) return "butterfly1";
		return "butterfly2";
	}
}
