package entity.player;

import Resources.Saver;
import entity.Entity.LookingVect;
import entity.GenericalHumanEntity;
import world.World;

public class PlayerEntity extends GenericalHumanEntity
{
	public int spawnX = 0;
	public int spawnY = 0;

	public PlayerEntity(World world, int uid)
	{
		super(world, "Player", 0, uid);
	}
	
	public void setPlayerSpawn(int x, int y)
	{
		spawnX = x; spawnY = y;
	}
	
	@Override
	public void attack()
	{
		System.out.println("attack!!");
	}
	
	@Override
	public String getTexture()
	{
		String ret = "miss";
		
		if(this.currentMotionX == 0 || this.currentMotionY == 0)
		{
			if(this.lookingTo == LookingVect.E) ret = "playerEastStay" + (this.lifeTime % 60 / 10 + 1);
			if(this.lookingTo == LookingVect.W) ret = "playerWestStay" + (this.lifeTime % 60 / 10 + 1);
		}
		if(this.currentMotionX != 0 || this.currentMotionY != 0)
		{
			if(this.lookingTo == LookingVect.E) ret = "playerEastWalk" + (this.lifeTime % 60 / 10 + 1);
			if(this.lookingTo == LookingVect.W) ret = "playerWestWalk" + (this.lifeTime % 60 / 10 + 1);
		}
		
		return ret;
	}
	
	@Override
	public void updateLookingVector()
    {
    	if(this.motionX > 0) this.lookingTo = LookingVect.E;
    	else if(this.motionX < 0) this.lookingTo = LookingVect.W;
    	
    	if(this.lookingTo != LookingVect.E && this.lookingTo != LookingVect.W) this.lookingTo = LookingVect.E;
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
