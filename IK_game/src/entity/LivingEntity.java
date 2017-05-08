package entity;

import Game.Game;
import Resources.Saver;
import util.Navigator;
import world.World;

public class LivingEntity extends Entity
{
	public double maxHP = 10D;
	public double maxMP = 10D;
	
	public double HP = 10D;
	public double MP = 10D;
	public double Armor = 0D;
	public double MagicalResistance = 0D;
	
	public double HPRegeneration = 0.1D;
	public double MPRegeneration = 0.1D;
	
	public double lastHPRestoring = 0;
	public double lastMPRestoring = 0;
	
	public int Level = 1;
	public int Experience = 1;
	
	public Navigator navigator = new Navigator(this);
	
	public LivingEntity latestAttacker = null;
	public int lastAttackerUID = -1;

	public LivingEntity(World world, String unlocalizedname, int id, int uid)
	{
		super(world, unlocalizedname, id, uid);
	}
	
	public void setMaxHP(double maxHP)
	{
		this.maxHP = maxHP;
	}
	
	public double getMaxHP()
	{
		return this.maxHP;
	}
	
	public void setMaxMP(double maxMP)
	{
		this.maxMP = maxMP;
	}
	
	public double getMaxMP()
	{
		return this.maxMP;
	}
	
	@Override
	public void onEntityUpdate()
    {
		if(lastAttackerUID >= 0 && latestAttacker == null) latestAttacker = (LivingEntity) world.getEntityByUID(lastAttackerUID);
		if(latestAttacker == null && lastAttackerUID >= 0) lastAttackerUID = -1;
		
		if(latestAttacker != null && !latestAttacker.isEntityAlive()) latestAttacker = null;
		
		if(HP > maxHP) HP = maxHP;
		if(MP > maxMP) MP = maxMP;
		
		if(HP < maxHP && lastHPRestoring + 30 < this.lifeTime)
		{
			restoreHP(this.HPRegeneration);
			lastHPRestoring = this.lifeTime;
		}
		if(MP < maxMP && lastMPRestoring + 30 < this.lifeTime)
		{
			restoreMP(this.MPRegeneration);
			lastMPRestoring = this.lifeTime;
		}
		
		if(HP <= 0) this.kill();
		if(MP < 0) MP = 0D;
		
		if(world.getController().getControllableEntity() != this)
		{
			if(!this.navigator.noPath())
			{
				double[] point = this.navigator.getCurrentPoint();
				
				if(point != null)
				{
					if(point[0] < this.getX()) this.motionX = -motion;
					if(point[0] > this.getX()) this.motionX = motion;
					
					if(point[1] < this.getY()) this.motionY = -motion;
					if(point[1] > this.getY()) this.motionY = motion;

					this.navigator.Update();
				}
			}
		}
		
		super.onEntityUpdate();
    }
	
	public void restoreHP(double hp)
	{
		HP += hp;
	}
	
	public void restoreMP(double mp)
	{
		MP += mp;
	}
	
	@Override
	public void attackFrom(damageSource damageType, double value)
	{
		double dl = 0.75D;
		String dls = Game.theGame.gameSettingSaver.getString("DifficultyLevel");
		
		if(dls.equals("Easy")) dl = 1D;
		if(dls.equals("Hard")) dl = 0.5D;
		
		double DMG = value;
		
		if(damageType == damageSource.PHYSICAL) DMG = value - Armor * dl;
		else if(damageType == damageSource.MAGICAL) DMG = value - MagicalResistance * dl;
		
		if(DMG < 1D) DMG = 1D;
		
		HP -= DMG;
		if(HP <= 0D)
		{
			this.isDead = true;
		}
		super.attackFrom(damageType, value);
	}
	
	@Override
	public void attackFromLivingEntity(LivingEntity e, damageSource damageType, double value)
	{
		latestAttacker = e;
		lastAttackerUID = e.uid;
		
		this.attackFrom(damageType, value);
		super.attackFromLivingEntity(e, damageType, value);
		
		e.currentMotionX = -e.currentMotionX;
		e.currentMotionY = -e.currentMotionY;
		this.currentMotionX = -e.currentMotionX * 2;
		this.currentMotionY = -e.currentMotionY * 2;
	}

	@Override
	public void writeToSaver(Saver saver, int i)
	{
		super.writeToSaver(saver, i);
		saver.addDouble(HP, "EntityHP"+i);
		saver.addDouble(MP, "EntityMP"+i);
		saver.addDouble(Armor, "EntityArmor"+i);
		saver.addDouble(MagicalResistance, "EntityMagicalResistance"+i);
		saver.addDouble(HPRegeneration, "EntityHPRegeneration"+i);
		saver.addDouble(MPRegeneration, "EntityMPRegeneration"+i);
		saver.addInt(Level, "EntityLevel"+i);
		if(latestAttacker != null) saver.addInt(latestAttacker.uid, "EntityLatestAttackerUid"+i);
		else saver.addInt(-1, "EntityLatestAttackerUid"+i);
	}
	
	@Override
	public void readFromSaver(Saver saver, int i)
	{
		super.readFromSaver(saver, i);
		HP = saver.getDouble("EntityHP"+i);
		MP = saver.getDouble("EntityMP"+i);
		Armor = saver.getDouble("EntityArmor"+i);
		MagicalResistance = saver.getDouble("EntityMagicalResistance"+i);
		HPRegeneration = saver.getDouble("EntityHPRegeneration"+i);
		MPRegeneration = saver.getDouble("EntityMPRegeneration"+i);
		Level = saver.getInt("EntityLevel"+i);
		this.lastAttackerUID = saver.getInt("EntityLatestAttackerUid"+i);
	}
}


















