package entity.passive;

import world.World;

public class Entity_Butterfly extends PassiveEntity
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
		
		this.motion = 0.75;
		this.setMoveSpeed(0.05);
	}
	
	@Override
	public void onEntityUpdate()
	{
		if(this.navigator.noPath())
		{
			double toX = this.getX() + (int)(Math.random()*3) - 1;
			double toY = this.getY() + (int)(Math.random()*3) - 1;
			
			while(!this.canStayAt(toX, toY))
			{
				toX = this.getX() + (int)(Math.random()*3) - 1;
				toY = this.getY() + (int)(Math.random()*3) - 1;
			}
			
			this.navigator.setDeviation(0.1);
			this.navigator.tryMoveToXY(toX, toY);
		}
		
		super.onEntityUpdate();
	}


	@Override
	public String getTexture()
	{
		if(this.lifeTime % 10 < 5) return "butterfly1";
		return "butterfly2";
	}
	
	@Override
	public boolean shouldDespawn()
    {
		if(world.getController().getControllableEntity() != null && world.getController().getControllableEntity().getDistanceToEntity(this) > 32) return true;
    	return false;
    }
}
