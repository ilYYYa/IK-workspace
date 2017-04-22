package entity;

import Game.Game;
import entity.Entity.damageSource;
import world.World;

public class LivingEntity extends Entity
{
	public double HP = 10D;
	public double MP = 10D;
	public double Armor = 0D;
	public double MagicalResistance = 0D;
	
	public double HPRegeneration = 0.1D;
	public double MPRegeneration = 0.1D;
	
	public int Level = 1;
	
	public LivingEntity latestAttacker = null;

	public LivingEntity(World world, String unlocalizedname, int id, int uid)
	{
		super(world, unlocalizedname, id, uid);
	}
	
	@Override
	public void attack(damageSource damageType, double value)
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
		super.attack(damageType, value);
	}
	
	@Override
	public void attackFromLivingEntity(LivingEntity e, damageSource damageType, double value)
	{
		latestAttacker = e;
		this.attack(damageType, value);
		super.attackFromLivingEntity(e, damageType, value);
	}
}
