package trigger;

import entity.Entity;
import entity.monster.Entity_Slime;
import world.World;

public class Trigger_SlimeSpawner extends Trigger
{
	int cooldown = 0;
	
	public Trigger_SlimeSpawner(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		this.unlocalizedTriggerName = "slimespawner";
	}

	@Override
	public void onTriggerOnScreen(World world)
	{
		
	}

	@Override
	public void onTriggerNotOnScreen(World world)
	{
		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		int slimesInWorld = world.getEntitiesInWorldByClass(Entity_Slime.class).size();
		if(slimesInWorld >= 10) return;
		
		int slimesCountNearly = world.getEntitiesInWorldByClassAtPosWithRadius(Entity_Slime.class, this.posX + this.width/2, this.posY + this.height/2, Math.sqrt(width*width + height*height)/2 + 8).size(); 
		
		double spX = (int)(this.posX + Math.random() * (double)(width - 1)) + 0.5D;
		double spY = (int)(this.posY + Math.random() * (double)(height - 1)) + 1D;
		
		if(slimesCountNearly < this.width*this.height && world.getPlayer() != null && world.theWorldRenderer != null
				&& world.getPlayer().getDistanceTo(spX, spY) < world.theWorldRenderer.blocksAtScreenByWidth + 32)
		{
			Entity_Slime slime = new Entity_Slime(world, 2);
			slime.setPosition(spX, spY);
			world.spawnEntity(slime);
		}
		
		cooldown = 240;
	}

	@Override
	public void onEntityInTrigger(World world, Entity entity)
	{
		
	}

	@Override
	public void onTriggerUpdate(World world)
	{
		
	}
}
