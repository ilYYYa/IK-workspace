package entity.player;

import Resources.Saver;
import entity.LivingEntity;
import entity.Entity.LookingVect;
import world.World;

public class PlayerEntity extends LivingEntity
{
	public int spawnX = 0;
	public int spawnY = 0;

	public PlayerEntity(World world, int uid)
	{
		super(world, "Player", 0, uid);
		this.setWidth(1D);
		this.setHeight(1.7D);
	}
	
	public void setPlayerSpawn(int x, int y)
	{
		spawnX = x; spawnY = y;
	}
	
	@Override
	public String getTexture()
	{
		if(this.lookingTo == LookingVect.EAST && this.motionX > 0 && this.lifeTime % 20 < 10) return "playerEast1";
		else if(this.lookingTo == LookingVect.EAST && this.motionX > 0 && this.lifeTime % 20 >= 10) return "playerEast2";
		else if(this.lookingTo == LookingVect.EAST && this.motionX == 0) return "playerEastStay";
		else if(this.lookingTo == LookingVect.WEST && this.motionX < 0 && this.lifeTime % 20 < 10) return "playerWest1";
		else if(this.lookingTo == LookingVect.WEST && this.motionX < 0 && this.lifeTime % 20 >= 10) return "playerWest2";
		else if(this.lookingTo == LookingVect.WEST && this.motionX == 0) return "playerWestStay";
		else if(this.lookingTo == LookingVect.SOUTH && this.motionY > 0 && this.lifeTime % 20 < 10) return "playerSouth1";
		else if(this.lookingTo == LookingVect.SOUTH && this.motionY > 0 && this.lifeTime % 20 >= 10) return "playerSouth2";
		else if(this.lookingTo == LookingVect.SOUTH && this.motionY == 0) return "playerSouthStay";
		else if(this.lookingTo == LookingVect.NORTH && this.motionY < 0 && this.lifeTime % 20 < 10) return "playerNorth1";
		else if(this.lookingTo == LookingVect.NORTH && this.motionY < 0 && this.lifeTime % 20 >= 10) return "playerNorth2";
		else if(this.lookingTo == LookingVect.NORTH && this.motionY == 0) return "playerNorthStay";
		
		return "miss";
	}
	

	@Override
	public void writeToSaver(Saver saver, int i)
	{
		super.writeToSaver(saver, i);
	}
	
	@Override
	public void readFromSaver(Saver saver, int i)
	{
		super.readFromSaver(saver, i);
	}

	public void respawn()
	{
		this.isDead = false;
		this.currentMotionX = 0;
		this.currentMotionY = 0;
		this.motionX = 0;
		this.motionY = 0;
		this.setPosition(spawnX + 0.5, spawnY + 1);
		if(world.getEntityIndex(this) == -1) world.setPlayer(this);
	}
}
