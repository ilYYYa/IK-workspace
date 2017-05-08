package item.crystal;

import entity.LivingEntity;
import item.Item;

public abstract class ItemCrystal extends Item
{
	public ItemCrystal(int itemId, String itemUnlocalizedName)
	{
		super(itemId, itemUnlocalizedName);
	}

	public void onEntityUse(LivingEntity entity)
	{
		if(shouldExecuteByEntityUse(entity)) this.startExecute(entity);
	}

	public abstract boolean shouldExecuteByEntityUse(LivingEntity entity);
	public abstract boolean shouldExecuteByTick(LivingEntity entity);
	public abstract void startExecute(LivingEntity entity);
	public abstract boolean continueExecute(LivingEntity entity);
	public abstract void stopExecute(LivingEntity entity);
}
