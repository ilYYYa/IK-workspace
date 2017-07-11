package entity.passive;

import entity.LivingEntity;
import entity.Entity.damageSource;
import world.World;

public class PassiveEntity extends LivingEntity
{
	int stayCD = 0;
	
	public PassiveEntity(World world, String unlocalizedname, int id, int uid)
	{
		super(world, unlocalizedname, id, uid);
	}
	
	@Override
	public void onEntityUpdate()
	{
		if(this.navigator.noPath() && stayCD <= 0)
		{
			int toX = (int)(this.getX() + (int)(Math.random() * 16) - 8);
			int toY = (int)(this.getY() + (int)(Math.random() * 16) - 8);
			
			while(!this.canMoveTo(toX, toY))
			{
				toX = (int)(this.getX() + (int)(Math.random() * 16) - 8);
				toY = (int)(this.getY() + (int)(Math.random() * 16) - 8);
			}
			
			this.navigator.tryMoveToXY(toX, toY);
			
			stayCD = 240;
		}
		else if(this.navigator.noPath() && stayCD > 0)
		{
			stayCD--;
		}
		
		super.onEntityUpdate();
	}

}
