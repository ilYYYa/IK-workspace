package entity;

import Resources.Saver;
import world.BlockPos;
import world.World;

public class Entity
{
	public double posX = 0;
	public double posY = 0;
	
	public double motionX = 0;
	public double motionY = 0;
	
	public int id = 0;
	public int uid = 0;
	public String unlocalizedName = "";
	
	public double width = 0D;
	public double height = 0D;
	
	public boolean isDead = false;
	
	public long lifeTime = 0;
	
	public boolean collisedWithBlocks = true;
	
	public World world;
	
	public Entity(World world, String unlocalizedname, int id, int uid)
	{
		this.world = world;
		this.unlocalizedName = unlocalizedname;
		this.id = id;
		this.uid = uid;
	}
	
	public void attack(damageSource damageType, double value)
	{
		
	}
	
	public void attackFromLivingEntity(LivingEntity e, damageSource damageType, double value)
	{
		
	}
	
	public void setPosition(double x, double y)
	{
		posX = x; posY = y;
	}
	
	public BlockPos getPosition()
	{
		return new BlockPos((int)(posX + width/2), (int)(posY + height/2), BlockPos.blockPosLevel.MIDDLE);
	}
	
	public double getDistanceSQTo(double x, double y)
	{
		return (posX - x)*(posX - x) + (posY - y)*(posY - y);
	}
	public double getDistanceTo(double x, double y)
	{
		return Math.sqrt(getDistanceSQTo(x, y));
	}
	public double getDistanceToBlockPos(BlockPos pos)
	{
		return getDistanceTo(pos.posX, pos.posY);
	}
	public double getDistanceToEntity(Entity entity)
	{
		return getDistanceTo(entity.posX, entity.posY);
	}
	
    public boolean isEntityAlive()
    {
        return !this.isDead;
    }
    
    public void kill()
    {
    	this.isDead = true;
    }
    
    public void onEntityUpdate()
    {
    	lifeTime++;
    }
    
    public static enum damageSource
    {
    	PHYSICAL, MAGICAL, PURE
    }

	public void writeToSaver(Saver saver, int i)
	{
		saver.addString(unlocalizedName, "EntityUnlocalizedName" + i);
		saver.addInt(id, "EntityId" + i);
		saver.addInt(uid, "EntityUid" + i);
		saver.addDouble(posX, "EntityPosX"+i);
		saver.addDouble(posY, "EntityPosY"+i);
		saver.addDouble(motionX, "EntityMotionX"+i);
		saver.addDouble(motionY, "EntityMotionY"+i);
		saver.addDouble(width, "EntityWidth"+i);
		saver.addDouble(height, "EntityHeight"+i);
		saver.addBoolean(isDead, "EntityIsDead"+i);
		saver.addLong(lifeTime, "EntityLifeTime"+i);
	}

	public void readFromSaver(Saver saver, int i)
	{
		unlocalizedName = saver.getString("EntityUnlocalizedName" + i);
		id = saver.getInt("EntityId" + i);
		uid = saver.getInt("EntityUid" + i);
		posX = saver.getDouble("EntityPosX" + i);
		posY = saver.getDouble("EntityPosY" + i);
		motionX = saver.getDouble("EntityMotionX" + i);
		motionY = saver.getDouble("EntityMotionY" + i);
		width = saver.getDouble("EntityWidth" + i);
		height = saver.getDouble("EntityHeight" + i);
		isDead = saver.getBoolean("EntityIsDead" + i);
		lifeTime = saver.getLong("EntityLifeTime" + i);
	}
}





















