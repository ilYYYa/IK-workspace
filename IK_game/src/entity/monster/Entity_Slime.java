package entity.monster;

import world.World;

public class Entity_Slime extends MonsterEntity
{
	int movingCD = 0;

	public Entity_Slime(World world, int uid)
	{
		super(world, "Slime", 1, uid);
		
		this.setWidth(0.9);
		this.setHeight(0.9);
		this.setHitboxWidth(0.9);
		
		this.setMaxHP(10D);
		this.setMaxMP(0D);
		
		this.motion = 0.75 + Math.random();
	}

	@Override
	public String getTexture()
	{
		if(this.lifeTime % 40 < 10) return "slime1";
		if(this.lifeTime % 40 >= 10 && this.lifeTime % 40 < 20) return "slime2";
		if(this.lifeTime % 40 >= 20 && this.lifeTime % 40 < 30) return "slime1";
		return "slime3";
	}
}
