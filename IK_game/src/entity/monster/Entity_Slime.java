package entity.monster;

import java.util.ArrayList;

import entity.LivingEntity;
import entity.passive.Entity_Butterfly;
import world.World;

public class Entity_Slime extends MonsterEntity
{
	int movingCD = 0;

	public Entity_Slime(World world, int uid)
	{
		super(world, "Slime", 1, uid);
		
		this.setWidth(0.4);
		this.setHeight(0.4);
		this.setHitboxWidth(0.4);
		this.setHitboxHeight(0.4);
		
		this.setMaxHP(2D);
		this.setMaxMP(0D);
		
		this.motion = 0.5 + Math.random()/3;
		this.setMoveSpeed(0.075);
	}

	@Override
	public String getTexture()
	{
		if(this.lifeTime % 40 < 10) return "slime1";
		if(this.lifeTime % 40 >= 10 && this.lifeTime % 40 < 20) return "slime2";
		if(this.lifeTime % 40 >= 20 && this.lifeTime % 40 < 30) return "slime1";
		return "slime3";
	}
	
	@Override
	public LivingEntity getTriggeredEntityForMonster()
	{
		LivingEntity ret = super.getTriggeredEntityForMonster();
		ArrayList<LivingEntity> buff = world.getEntitiesInWorldByClassAtPosWithRadius(Entity_Butterfly.class, this.getX(), this.getY(), 32.0D);
		if(buff.size() != 0 && (ret == null || this.getDistanceToEntity(ret) > 16D)) ret = buff.get(0);
		
		return ret;
	}
}
