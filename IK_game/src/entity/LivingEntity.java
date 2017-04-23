package entity;

import Game.Game;
import Resources.Saver;
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
		saver.addInt(latestAttacker.uid, "EntityLatestAttackerUid"+i);
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


















