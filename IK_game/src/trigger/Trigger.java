package trigger;

import Resources.Saver;
import entity.Entity;
import world.BlockPos;
import world.World;

public abstract class Trigger
{
	public String unlocalizedTriggerName = "trigger";
	
	public int triggerId = 0;
	
	public int posX = 0;
	public int posY = 0;
	public int width = 0;
	public int height = 0;
	
	public Trigger(int x, int y, int width, int height)
	{
		posX = x; posY = y;
		this.width = width; this.height = height;
	}
	
	public abstract void onTriggerOnScreen(World world);
	
	public abstract void onTriggerNotOnScreen(World world);
	
	public abstract void onEntityInTrigger(World world, Entity entity);

	public abstract void onTriggerUpdate(World world);

	public boolean isCrossingPoint(double x, double y)
	{
		if(x >= posX && x <= posX + width && y >= posY && y <= posY + height) return true;
		return false;
	}
}
