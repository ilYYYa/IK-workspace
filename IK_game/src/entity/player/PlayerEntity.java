package entity.player;

import Resources.Saver;
import entity.passive.PassiveEntity;
import world.World;

public class PlayerEntity extends PassiveEntity
{
	public int spawnX = 0;
	public int spawnY = 0;

	public PlayerEntity(World world, int uid)
	{
		super(world, "Player", 0, uid);
		this.setWidth(1.6D);
		this.setHeight(1.6D);
		this.setHitboxWidth(0.8);
		this.setHitboxHeight(0.8D);
		this.motion = 1.4;
		this.setMoveSpeed(0.2);
		this.setMaxHP(20D);
	}
	
	public void setPlayerSpawn(int x, int y)
	{
		spawnX = x; spawnY = y;
	}
	
	@Override
	public String getTexture()
	{
		String ret = "miss";
		
		if(this.currentMotionX == 0 && this.currentMotionY == 0)
		{
			int a = (int)(this.lifeTime % 60 / 10 + 1);
			if(a > 4) a = Math.abs(a - 8);
			ret = "player" + this.lookingTo + a;
		}
		if(this.currentMotionX != 0 || this.currentMotionY != 0)
		{
			int a = (int)(this.lifeTime % 60 / 10 + 1);
			ret = "player" + this.lookingTo + "M" + a;
		}
		
		return ret;
	}
	
	@Override
	public void updateLookingVector()
    {
    	if(this.motionX > 0 && this.motionY > 0) this.lookingTo = LookingVect.SE;
    	else if(this.motionX == 0 && this.motionY > 0) this.lookingTo = LookingVect.S;
    	else if(this.motionX < 0 && this.motionY > 0) this.lookingTo = LookingVect.SW;
    	else if(this.motionX > 0 && this.motionY == 0) this.lookingTo = LookingVect.E;
    	else if(this.motionX < 0 && this.motionY == 0) this.lookingTo = LookingVect.W;
    	else if(this.motionX > 0 && this.motionY < 0) this.lookingTo = LookingVect.NE;
    	else if(this.motionX == 0 && this.motionY < 0) this.lookingTo = LookingVect.N;
    	else if(this.motionX < 0 && this.motionY < 0) this.lookingTo = LookingVect.NW;
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
