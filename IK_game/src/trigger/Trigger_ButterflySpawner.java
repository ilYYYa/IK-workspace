package trigger;

import entity.Entity;
import entity.monster.Entity_Slime;
import entity.passive.Entity_Butterfly;
import world.World;

public class Trigger_ButterflySpawner extends Trigger
{

	int cooldown = 0;
	public Trigger_ButterflySpawner(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		this.unlocalizedTriggerName = "butterflyspawner";
	}

	@Override
	public void onTriggerOnScreen(World world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTriggerNotOnScreen(World world) {

		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		int butterblysInTheWorld = world.getEntitiesInWorldByClass(Entity_Butterfly.class).size();
		if(butterblysInTheWorld >= 10) return;

		Entity_Butterfly btf = new Entity_Butterfly(world, cooldown);
		btf.setPosition(this.posX, this.posY);
		world.spawnEntity(btf);
	}

	@Override
	public void onEntityInTrigger(World world, Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTriggerUpdate(World world) {
		// TODO Auto-generated method stub
		
	}

}
