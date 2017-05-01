package entity.monster;

import entity.LivingEntity;
import world.World;

public class MonsterEntity extends LivingEntity
{
	int movingCD = 0;

	public MonsterEntity(World world, String unlocalizedname, int id, int uid)
	{
		super(world, unlocalizedname, id, uid);
	}
	
	@Override
	public void onEntityUpdate()
	{
		if(this.navigator.noPath() && movingCD <= 0)
		{
			int toX = (int)this.getX() + (int)(Math.random() * 16) - 8;
			int toY = (int)this.getY() + (int)(Math.random() * 16) - 8;
			
			while(!this.canMoveTo(toX, toY))
			{
				toX = (int)this.getX() + (int)(Math.random() * 16) - 8;
				toY = (int)this.getY() + (int)(Math.random() * 16) - 8;
			}
			
			this.navigator.tryMoveToXY(toX, toY);
			movingCD = 240;
		}
		else if(this.navigator.noPath() && movingCD > 0)
		{
			movingCD--;
		}
		
		if(world.getPlayer() != null && world.getDistanceToPlayerFromEntity(this) <= 32 &&
				((world.getGamemode() == 0 && world.getController().getControllableEntity() == world.getPlayer()) || (world.getGamemode() != 0 && world.getController().getControllableEntity() != world.getPlayer())) &&
				world.getPlayer().isEntityAlive())
		{
			if(!navigator.noPath() && !navigator.movingToEntity())
			{
				navigator.removePath();
			}
			if(navigator.noPath())
			{
				this.navigator.tryMoveToEntity(world.getPlayer());
			}
			
			if(this.collisionWithEntity(world.getPlayer()))
			{
				world.getPlayer().attackFromLivingEntity(this, damageSource.PHYSICAL, 1D);
			}
		}
		
		super.onEntityUpdate();
	}
	
	@Override
	public boolean shouldDespawn()
	{
		if(this.world.theWorldRenderer == null && world.worldTicks > 25) return true;
		if(this.getDistanceTo(this.world.theWorldRenderer.cameraPosX, this.world.theWorldRenderer.cameraPosY) > this.world.theWorldRenderer.blocksAtScreenByWidth + 64) return true;
		
		return false;
	}
}
