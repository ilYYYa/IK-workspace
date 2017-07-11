package entity.monster;

import entity.Entity;
import entity.LivingEntity;
import entity.Entity.damageSource;
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
		
		if(this.latestAttacker == null) this.latestAttacker = getTriggeredEntityForMonster();
		
		if(latestAttacker != null && this.getDistanceToEntity(latestAttacker) <= 32 && latestAttacker.isEntityAlive())
		{
			if(!navigator.noPath() && !navigator.movingToEntity())
			{
				navigator.removePath();
			}
			if(navigator.noPath())
			{
				this.navigator.tryMoveToEntity(latestAttacker);
			}

			if(this.collisionWithEntityByHitbox(latestAttacker))
			{
				this.attack(latestAttacker, damageSource.PHYSICAL, this.getEntityDamage());
			}
		}
		else
		{
			latestAttacker = null;
		}
		
		super.onEntityUpdate();
	}
	
	@Override
	public boolean attack(Entity entity, damageSource damageType, double value)
	{
		if(super.attack(entity, damageType, value))
		{
			this.currentMotionX = -(this.currentMotionX*2);
			this.currentMotionY = -(this.currentMotionY*2);
			return true;
		}
		else return false;
	}
	
	public LivingEntity getTriggeredEntityForMonster()
	{
		LivingEntity player = world.getPlayer();
		
		if(world.getController().getControllableEntity() != player) return null;
		if(world.getGamemode() == 1) return null;
		if(!player.isEntityAlive()) return null;
		if(this.getDistanceToEntity(player) > 16D) return null;
		
		return player;
	}
	
	@Override
	public boolean shouldDespawn()
	{
		if(this.world.theWorldRenderer == null && world.worldTicks > 25) return true;
		if(this.getDistanceTo(this.world.theWorldRenderer.cameraPosX, this.world.theWorldRenderer.cameraPosY) > this.world.theWorldRenderer.blocksAtScreenByWidth + 64) return true;
		
		return false;
	}
}
