package entity;

import Game.Game;
import Resources.Saver;
import entity.Entity.damageSource;
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
	
	public Navigator navigator = new Navigator(this);
	
	public LivingEntity latestAttacker = null;

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
		
		if(!this.navigator.noPath())
		{
			double[] point = this.navigator.getCurrentPoint();
			
			if(point != null)
			{
				if(point[0] < this.getX()) this.motionX = -1.5D;
				if(point[0] > this.getX()) this.motionX = 1.5D;
				
				if(point[1] < this.getY()) this.motionY = -1.5D;
				if(point[1] > this.getY()) this.motionY = 1.5D;

				this.navigator.Update();
			}
		}
		else if(!(this instanceof PlayingPlayerEntity))
		{
			this.motionX = 0;
			this.motionY = 0;
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
		//latestAttacker.uid = saver.getInt("EntityLatestAttackerUid"+i);
	}
}


















