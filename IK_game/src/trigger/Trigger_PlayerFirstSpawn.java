package trigger;

import entity.Entity;
import entity.player.PlayerEntity;
import world.World;

public class Trigger_PlayerFirstSpawn extends Trigger
{

	public Trigger_PlayerFirstSpawn(int x, int y)
	{
		super(x, y, 1, 1);
		this.unlocalizedTriggerName = "playerspawner";
	}

	@Override
	public void onTriggerOnScreen(World world)
	{
		
	}

	@Override
	public void onTriggerNotOnScreen(World world)
	{
		
	}

	@Override
	public void onEntityInTrigger(World world, Entity entity)
	{
		
	}

	@Override
	public void onTriggerUpdate(World world)
	{
		if(world.getPlayer() == null)
		{
			PlayerEntity player = new PlayerEntity(world, 1);
			player.setPosition(this.posX + 0.5D, this.posY + 1);
			
			player.setPlayerSpawn(this.posX, this.posY);
			
			world.setPlayer(player);
		}
	}

}
